package com.oasis_hotel.oasis_hotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomUpdateRequestDTO;

public interface RoomService {
    RoomResponseDTO createRoom(RoomRequestDTO dto);
    Page<RoomResponseDTO> getAllRooms(Pageable pageable);
    RoomResponseDTO getRoomById(Long id);
    RoomResponseDTO updateRoom(Long id, RoomUpdateRequestDTO request);
}
