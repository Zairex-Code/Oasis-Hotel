package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.auth.AuthResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.auth.LoginRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.User;
import com.oasis_hotel.oasis_hotel.mapper.UserMapper;
import com.oasis_hotel.oasis_hotel.repository.UserRepository;
import com.oasis_hotel.oasis_hotel.service.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login( @Valid @RequestBody LoginRequestDTO request) {

        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userRepository.findByEmail(request.email()).orElseThrow();

        String token = jwtService.generateToken(user.getEmail(),user.getRole().name());

        UserResponseDTO userResponse = userMapper.toResponse(user);
        
        return ResponseEntity.ok(new AuthResponseDTO(token, userResponse));
    }
    



}
