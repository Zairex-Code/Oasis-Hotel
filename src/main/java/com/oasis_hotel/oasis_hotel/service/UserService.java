package com.oasis_hotel.oasis_hotel.service;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;

public interface UserService  {
    UserResponseDTO createUser(UserRequestDTO dto);
    
}
