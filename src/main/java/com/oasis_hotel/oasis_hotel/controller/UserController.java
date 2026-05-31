package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO newUser = userService.createUser(request);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    


    
}
