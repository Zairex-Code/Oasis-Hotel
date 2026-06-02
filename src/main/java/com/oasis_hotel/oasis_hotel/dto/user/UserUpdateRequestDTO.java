package com.oasis_hotel.oasis_hotel.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
    @NotBlank(message="Name must not be empty")
    @Size( max=30, message="First name cannot exceed 30 characters")
    String firstName,

    @NotBlank(message="last name must not be empty")
    @Size(max=30, message="last name cannot exceed 30 characters")
    String lastName,

    @NotBlank(message="email must not be empty")
    @Email(message="invalid email format")
    String email
) {
    
}
