package com.oasis_hotel.oasis_hotel.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotBlank(message = "first name is mandatory")
    @Size(min = 3 , max = 30, message = "first name must be between 3 and 30 characters")
    String firstName,

    @NotBlank(message="last name must be mandatory")
    @Size(min= 3 , max=30, message="last name must be between 3 and 30 characters")
    String lastName,

    @NotBlank(message="email must be mandatory")
    @Email(message="email should be valid")
    @Size(min=3, max=30, message="email must be between 3 and 100 characters")
    String email,

    @NotBlank(message="Password is mandatory")
    @Size(min=6, max=50, message="password must be between 8 and 50 characters")
    String password

) {
    
}
