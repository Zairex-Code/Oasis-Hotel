package com.oasis_hotel.oasis_hotel.dto.room;

import java.math.BigDecimal;

import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoomRequestDTO(
    @NotBlank(message = "room number is mandatory")
    @Size(max = 20, message = "Room number cannot exceed 20 characters")
    String roomNumber,

    @NotNull(message = "capacity is mandatory")
    @Min(value = 1, message = "capacity must be at least 1")
    Integer capacity,

    @NotNull(message = "price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than zero")
    BigDecimal pricePerNight,

    @NotNull(message = "room type is mandatory")
    RoomType roomType,

    @NotNull(message = "hotel id is mandatory")
    Long hotelId

    
    
) {
    
}
