package com.oasis_hotel.oasis_hotel.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "email is mandatory")
    @Email(message = "email format is not valid")
    String email,

    @NotBlank(message = "password is mandatory")
    String password
) {
    
}
