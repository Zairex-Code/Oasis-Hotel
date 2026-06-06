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

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;








@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Page<RoomResponseDTO>> getAllRooms(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<RoomResponseDTO> response = roomService.getAllRooms(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long id) {
        RoomResponseDTO response = roomService.getRoomById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hotelId/{hotelId}")
    public ResponseEntity<Page<RoomResponseDTO>> getMethodName(@PathVariable Long hotelId, @PageableDefault(size=10, page=0, sort="id") Pageable pageable) {

        Page<RoomResponseDTO> response = roomService.getRoomByHotelId(hotelId, pageable);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("search/room-capacity")
    public ResponseEntity<Page<RoomResponseDTO>> getMethodName(@RequestParam Integer capacity, @PageableDefault(size=10, page=0, sort="id") Pageable pageable) {
        Page<RoomResponseDTO> response = roomService.getRoomByCapacity(capacity, pageable);
        return ResponseEntity.ok(response);
    }
    
    
    
    
    @PostMapping()
    public ResponseEntity<RoomResponseDTO>createRoom(@Valid @RequestBody RoomRequestDTO request) {
        RoomResponseDTO newRoom = roomService.createRoom(request);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Long id,@Valid @RequestBody RoomUpdateRequestDTO request) {
        RoomResponseDTO response = roomService.updateRoom(id, request);

        return ResponseEntity.ok(response);
    }
    
}
