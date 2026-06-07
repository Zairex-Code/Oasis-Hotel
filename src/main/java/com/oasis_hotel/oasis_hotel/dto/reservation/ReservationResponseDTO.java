package com.oasis_hotel.oasis_hotel.dto.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;

public record ReservationResponseDTO(
    Long id,
    Long userId,
    String userFirstName,
    String userLastName,
    String userEmail,
    String hotelId,
    String hotelName,
    Long roomId,
    String roomNumber,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Integer numberOfGuests,
    BigDecimal totalPrice,
    ReservationStatus status,
    LocalDateTime createdAt

    
    
) {
    
}
