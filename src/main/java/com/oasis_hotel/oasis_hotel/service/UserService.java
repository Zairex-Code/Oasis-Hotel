package com.oasis_hotel.oasis_hotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserRoleRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserSetPasswordRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.Role;

public interface UserService  {
    UserResponseDTO createUser(UserRequestDTO dto);
    
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO getUserById(Long id);
    Page<UserResponseDTO> getUserByName(String name, Pageable pageable);
    UserResponseDTO getUserByEmail(String email);
    Page<UserResponseDTO> getUsersByRole(Role role,Pageable pageable);

    UserResponseDTO updateUser(Long id, UserUpdateRequestDTO request);


    UserResponseDTO setUserRole(Long id, UserRoleRequestDTO request);
    UserResponseDTO setUSerPassword(Long id, UserSetPasswordRequestDTO request);
    
}
