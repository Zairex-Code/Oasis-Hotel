package com.oasis_hotel.oasis_hotel.dto.user;

import java.time.LocalDateTime;

import com.oasis_hotel.oasis_hotel.entity.enums.Role;



public record UserResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    Role role,
    LocalDateTime createdAt
) {
    
}
