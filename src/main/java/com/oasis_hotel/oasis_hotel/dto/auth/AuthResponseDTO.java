package com.oasis_hotel.oasis_hotel.dto.auth;

import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;

public record AuthResponseDTO(
    String jwt,
    UserResponseDTO user
) {
    
}
