package com.oasis_hotel.oasis_hotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

public interface ReservationService {
    ReservationResponseDTO createReservation(ReservationRequestDTO request);
    ReservationResponseDTO getReservationById(Long id);
    Page<ReservationResponseDTO> getReservationsByUser(Long userId, Pageable pageable);
    Page<ReservationResponseDTO> getReservationByRoomType(RoomType roomType, Pageable pageable);
    ReservationResponseDTO cancelReservation(Long reservationId);
    
}
