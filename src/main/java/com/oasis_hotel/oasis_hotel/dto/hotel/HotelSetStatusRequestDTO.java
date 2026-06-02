package com.oasis_hotel.oasis_hotel.dto.hotel;

import com.oasis_hotel.oasis_hotel.entity.enums.HotelStatus;

import jakarta.validation.constraints.NotNull;

public record HotelSetStatusRequestDTO(
    @NotNull(message="Status is mandatory")
    HotelStatus status
) {
    
}
