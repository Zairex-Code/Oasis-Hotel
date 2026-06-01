package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/rooms")
public class RoomController {
    private final RoomService roomService;
    
    @PostMapping()
    public ResponseEntity<RoomResponseDTO>createRoom(@Valid @RequestBody RoomRequestDTO request) {
        RoomResponseDTO newRoom = roomService.createRoom(request);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }
    
}
