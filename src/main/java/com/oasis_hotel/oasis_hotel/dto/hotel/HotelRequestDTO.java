package com.oasis_hotel.oasis_hotel.dto.hotel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record HotelRequestDTO(
    @NotBlank(message="name is mandatory")
    @Size(min=3, max=100, message="name must be between 3 and 100 characters")
    String name,

    @NotBlank(message="address is mandatory")
    @Size(min=3, max=255, message="address must be between 3 and 255 characters")
    String address,

    @NotBlank(message="city is mandatory")
    @Size(min=3, max=30, message="city mast be between 3 and 30 characters")
    String city,

    @Min(value = 1, message = "Stars must be at least 1")
    @Max(value = 5, message = "Stars cannot be more than 5")
    Integer stars

) {
    
}
