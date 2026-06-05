package com.oasis_hotel.oasis_hotel.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.user.UserRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserSetPasswordRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.user.UserUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.Role;
import com.oasis_hotel.oasis_hotel.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;




@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final UserService userService;

    
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUser(@PageableDefault Pageable pageable) {
        Page<UserResponseDTO> response = userService.getAllUsers(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDTO>> getUserByName(@RequestParam String name, @PageableDefault(size = 10, page = 0, sort = "name") Pageable pageable) {
        Page<UserResponseDTO> response = userService.getUserByName(name, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/role")
    public ResponseEntity<Page<UserResponseDTO>> getUserByRole(@RequestParam Role role, @PageableDefault(size = 10, page = 0,sort = "id") Pageable pageable) {
        Page<UserResponseDTO> response = userService.getUsersByRole(role, pageable);
        return ResponseEntity.ok(response);
    }
    
    
    
    

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {
        UserResponseDTO newUser = userService.createUser(request);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO userUpdated = userService.updateUser(id, request);

        return ResponseEntity.ok(userUpdated);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<UserResponseDTO> setUserPassword(@PathVariable Long id,@Valid @RequestBody  UserSetPasswordRequestDTO request) {
        UserResponseDTO userPasswordUpdated = userService.setUSerPassword(id, request);

        return ResponseEntity.ok(userPasswordUpdated);
        
    }
    


    
}
