package com.oasis_hotel.oasis_hotel.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

public interface RoomService {
    RoomResponseDTO createRoom(RoomRequestDTO dto);
    Page<RoomResponseDTO> getAllRooms(Pageable pageable);
    RoomResponseDTO getRoomById(Long id);
    Page<RoomResponseDTO> getRoomByHotelId(Long hotelId, Pageable pageable);
    Page<RoomResponseDTO> getRoomByCapacity(Integer capacity, Pageable pageable);
    Page<RoomResponseDTO> getRoomByStatus(RoomStatus status, Pageable pageable);
    Page<RoomResponseDTO> getRoomByType(RoomType type, Pageable pageable);
    Page<RoomResponseDTO> getRoomByPriceRange(Long hotelId,BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    RoomResponseDTO updateRoom(Long id, RoomUpdateRequestDTO request);
}
