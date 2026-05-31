package com.oasis_hotel.oasis_hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.User;


@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponseDTO toResponse(User user);
    User toEntity(UserRequestDTO request);
    
}
