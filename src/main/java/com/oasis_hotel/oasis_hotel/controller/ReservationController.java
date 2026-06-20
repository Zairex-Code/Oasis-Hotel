package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;
import com.oasis_hotel.oasis_hotel.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;







@RestController
@RequestMapping("/v1/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('HOTEL_MANAGER')")
    @GetMapping 
    public ResponseEntity<Page<ReservationResponseDTO>> getAllReservations(@PageableDefault(size=10, page=0, sort="createdAt", direction=Direction.DESC) Pageable pageable) {
        
        Page<ReservationResponseDTO> response = reservationService.getAllReservations(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO request) {
        
        ReservationResponseDTO reservation = reservationService.createReservation(request);
        
        return new ResponseEntity<>(reservation ,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReservationResponseDTO>> getReservationByUserId(@PathVariable Long userId, @PageableDefault(size=10, page=0, sort="createdAt",direction=Direction.DESC) Pageable pageable) {
        Page<ReservationResponseDTO> response = reservationService.getReservationsByUser(userId, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO>  getReservationById(@PathVariable Long id) {
        
        ReservationResponseDTO response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("search/room-type")
    public ResponseEntity<Page<ReservationResponseDTO>> getReservationByRoomType(@RequestParam RoomType roomType,@PageableDefault(size=10, page=0, sort="createdAt", direction = Direction.DESC) Pageable pageable) {
        Page<ReservationResponseDTO> response = reservationService.getReservationByRoomType(roomType, pageable) ;
        
        return ResponseEntity.ok(response);
    }
    
    
    

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@Valid @PathVariable Long id) {
    

        ReservationResponseDTO response = reservationService.cancelReservation(id);

        return ResponseEntity.ok(response);
    }

    
}
