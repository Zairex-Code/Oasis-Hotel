package com.oasis_hotel.oasis_hotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;

public interface ReservationService {
    ReservationResponseDTO createReservation(ReservationRequestDTO request);
    Page<ReservationResponseDTO> getReservationsByUser(Long userId, Pageable pageable);
    ReservationResponseDTO cancelReservation(Long reservationId);
    
}
