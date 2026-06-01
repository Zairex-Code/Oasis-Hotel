package com.oasis_hotel.oasis_hotel.service;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;

public interface RoomService {
    RoomResponseDTO createRoom(RoomRequestDTO dto);
}
