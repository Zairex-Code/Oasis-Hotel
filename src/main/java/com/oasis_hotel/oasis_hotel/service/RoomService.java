package com.oasis_hotel.oasis_hotel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;

public interface RoomService {
    RoomResponseDTO createRoom(RoomRequestDTO dto);
    Page<RoomResponseDTO> getAllRooms(Pageable pageable);
}
