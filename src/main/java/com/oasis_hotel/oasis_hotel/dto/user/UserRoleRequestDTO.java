package com.oasis_hotel.oasis_hotel.dto.user;

import com.oasis_hotel.oasis_hotel.entity.enums.Role;

import jakarta.validation.constraints.NotNull;

public record UserRoleRequestDTO(
    @NotNull(message="role is mandatory")
    Role role
) {
    
}
