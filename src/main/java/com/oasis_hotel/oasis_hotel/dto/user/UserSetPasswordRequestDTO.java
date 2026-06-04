package com.oasis_hotel.oasis_hotel.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSetPasswordRequestDTO(
    @NotBlank(message="Password is mandatory")
    @Size(min=6 , max=20, message="Password must be between 6 and 20 characters")
    String password
) {
    
}
