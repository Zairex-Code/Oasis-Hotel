package com.oasis_hotel.oasis_hotel.service.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Reservation;
import com.oasis_hotel.oasis_hotel.entity.Room;
import com.oasis_hotel.oasis_hotel.entity.User;
import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.Role;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;
import com.oasis_hotel.oasis_hotel.exception.ResourceNotFoundException;
import com.oasis_hotel.oasis_hotel.mapper.ReservationMapper;
import com.oasis_hotel.oasis_hotel.repository.ReservationRepository;
import com.oasis_hotel.oasis_hotel.repository.RoomRepository;
import com.oasis_hotel.oasis_hotel.repository.UserRepository;
import com.oasis_hotel.oasis_hotel.service.ReservationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationMapper reservationMapper;
    
    
    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        

        if(request.checkOutDate().isBefore(request.checkInDate())|| request.checkOutDate().isEqual(request.checkInDate())){
            throw new IllegalArgumentException("check out date must be al least 1 day after the check out");
        }

        User user = userRepository.findById(request.userId())
                                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + request.userId()) );

        Room room = roomRepository.findById(request.roomId())
                                .orElseThrow(()-> new ResourceNotFoundException("Room not found with id: " + request.roomId()));
        
        if(request.numberOfGuests() > room.getCapacity()){
            throw new IllegalArgumentException("This room doesn't have capacity for " + request.numberOfGuests() + " guests max capability :" + room.getCapacity() );
        }

        List<Reservation> conflicts = reservationRepository.findConflictingReservations(request.roomId(), request.checkInDate(), request.checkOutDate());

        if(!conflicts.isEmpty()){
            throw new IllegalStateException("sorry, this room is already booked");
        }

        // ChronoUnit is a modern way to count days between 2 dates
        long nights = ChronoUnit.DAYS.between(request.checkInDate(), request.checkOutDate());

        
        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(nights));

        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus(ReservationStatus.CONFIRMED);


        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toResponse(savedReservation);
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                                .orElseThrow(()->new ResourceNotFoundException("Reservation not found with id: " + id));

        validateReservationOwnership(reservation);
        
        return reservationMapper.toResponse(reservation);
    }

    @Override
    public Page<ReservationResponseDTO> getReservationsByUser(Long userId, Pageable pageable) {
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("USer not found with id: " + userId);
        }

        Page<Reservation> reservation = reservationRepository.findByUserId(userId, pageable);
        
        if(reservation.isEmpty()){
            throw new ResourceNotFoundException("reservation not found with id: " + userId);
        }
        return reservation.map(reservationMapper::toResponse);
    }

    @Override
    public ReservationResponseDTO cancelReservation(Long reservationId) {
        
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new ResourceNotFoundException("reservation not found with id: " + reservationId));
        
        if(reservation.getStatus().equals(ReservationStatus.CANCELLED)){
            throw new IllegalStateException("This reservation was already cancelled");
        }

        validateReservationOwnership(reservation);

        reservation.setStatus(ReservationStatus.CANCELLED);

        Reservation reservationSaved = reservationRepository.save(reservation);
        return reservationMapper.toResponse(reservationSaved);
    }

    @Override
    public Page<ReservationResponseDTO> getReservationByRoomType(RoomType roomType, Pageable pageable) {
    

        Page<Reservation> reservation = reservationRepository.findByRoomType(roomType, pageable);
        if (reservation.isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found with type: " + roomType);
        }
        return reservation.map(reservationMapper::toResponse);
    }

    /**
     * Security SHIELD (Anti-IDOR Protection)
     * Validates if the authenticated user from the JWT token is the legitimate owner
     * of the reservation, or if they hold the "ADMIN" role to manage overarching records
     * @param Reservation the target reservation entity to evaluate
     */

    private void validateReservationOwnership(Reservation reservation){
        // 1. Extract the logged-in user from the active spring security context
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Evaluate authorization rules: Must be an ADMIN or the exact  reservation owner 
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isOwner = reservation.getUser().getId().equals(currentUser.getId());

        // 3. Throw a hard Stop exception if neither condition is met, triggering the Custom 403 JSON response
        if(!isAdmin && !isOwner){
            throw new AccessDeniedException("Access Denied: you don't have permission to access or modify this reservation");
        }
    }


    


}
