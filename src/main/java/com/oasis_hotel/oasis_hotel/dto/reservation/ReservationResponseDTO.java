package com.oasis_hotel.oasis_hotel.dto.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

public record ReservationResponseDTO(
    Long id,
    Long userId,
    String userFirstName,
    String userLastName,
    String userEmail,
    Long hotelId,
    String hotelName,
    Long roomId,
    String roomNumber,
    RoomType roomType,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    Integer numberOfGuests,
    BigDecimal totalPrice,
    ReservationStatus status,
    LocalDateTime createdAt

    
    
) {
    
}
