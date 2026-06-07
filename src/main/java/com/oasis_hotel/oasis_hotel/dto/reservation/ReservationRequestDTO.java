package com.oasis_hotel.oasis_hotel.dto.reservation;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservationRequestDTO(
    @NotNull(message="user id is mandatory")
    Long userId,

    @NotNull(message="room id is mandatory")
    Long roomId,

    @NotNull(message="check in date is mandatory")
    @FutureOrPresent(message="Check-in date should be today or a future date")
    LocalDate chekInDate,

    @NotNull(message="check out date is mandatory")
    LocalDate checkOutDate,

    @NotNull(message="number of guests is mandatory")
    @Positive(message="number of guest must be greater than zero")
    Integer numberOfGuests

) {
    
}
