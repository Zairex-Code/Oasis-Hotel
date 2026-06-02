package com.oasis_hotel.oasis_hotel.dto.room;

import java.math.BigDecimal;

import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RoomUpdateRequestDTO(
    @NotBlank(message="Room number cannot be empty")
    @Size(max=20 , message="Room numeber cannot exceed more than 20 characters")
    String roomNumber,

    @NotBlank(message="capacity is mandatory")
    @Positive(message="capacity must be greater than zero")
    Integer capacity,

    @NotBlank(message="price per night is mandatory")
    @Positive(message="price per night must be greater than 0.0")
    BigDecimal pricePerNight,

    @NotBlank(message="Room type is mandatory")
    RoomType roomType,

    @NotBlank(message="Room status is mandatory")
    RoomStatus roomStatus



) {
    
}
