package com.oasis_hotel.oasis_hotel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.User;
import com.oasis_hotel.oasis_hotel.exception.ResourceNotFoundException;
import com.oasis_hotel.oasis_hotel.mapper.UserMapper;
import com.oasis_hotel.oasis_hotel.repository.UserRepository;
import com.oasis_hotel.oasis_hotel.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    

    @Override
    public UserResponseDTO createUser(UserRequestDTO request){
        User userToSave = userMapper.toEntity(request);
        User userSaved = userRepository.save(userToSave);
        return userMapper.toResponse(userSaved);

    }

    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO request) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        
        return userMapper.toResponse(user);
    }
}
