package com.oasis_hotel.oasis_hotel.service.impl;

import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.User;
import com.oasis_hotel.oasis_hotel.mapper.UserMapper;
import com.oasis_hotel.oasis_hotel.repository.UserRepository;
import com.oasis_hotel.oasis_hotel.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request){
        User userToSave = userMapper.toEntity(request);
        User userSaved = userRepository.save(userToSave);
        return userMapper.toResponse(userSaved);

    }
}
