package com.oasis_hotel.oasis_hotel.dto.user;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Role;

public record UserResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    Role role,
    LocalDateTime createdAt
) {
    
}
