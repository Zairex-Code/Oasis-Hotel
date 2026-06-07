package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;
import com.oasis_hotel.oasis_hotel.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/v1/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO request) {
        
        ReservationResponseDTO reservation = reservationService.createReservation(request);
        
        return new ResponseEntity<>(reservation ,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@Valid @PathVariable Long id) {
        //TODO: process PUT request

        ReservationResponseDTO response = reservationService.cancelReservation(id);

        return ResponseEntity.ok(response);
    }

    
}
