package com.oasis_hotel.oasis_hotel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserSetPasswordRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.User;
import com.oasis_hotel.oasis_hotel.entity.enums.Role;
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
    private final PasswordEncoder passwordEncoder;

    

    @Override
    public UserResponseDTO createUser(UserRequestDTO request){
        User userToSave = userMapper.toEntity(request);
        userToSave.setPassword(passwordEncoder.encode(request.password()));
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
    public Page<UserResponseDTO> getUserByName(String name, Pageable pageable) {
        Page<User> user = userRepository.findByFullNameContainingIgnoreCase(name, pageable);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("user not found with name: " + name);
        }

        return user.map(userMapper::toResponse);
        

    }


    

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO request) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());

        
        User userUpdated = userRepository.save(user);
        
        return userMapper.toResponse(userUpdated);
    }

    @Override
    public UserResponseDTO setUSerPassword(Long id, UserSetPasswordRequestDTO request) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id : " + id));

        user.setPassword(passwordEncoder.encode(request.password()));

        User userPasswordUpdated = userRepository.save(user);
        
        return userMapper.toResponse(userPasswordUpdated);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toResponse(user);
    }

    @Override
    public Page<UserResponseDTO> getUsersByRole(Role role, Pageable pageable) {

        Page<User> user = userRepository.findByRole(role, pageable);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Users not found with Role: " + role);
        }
        return user.map(userMapper::toResponse);
    }

}
