package com.oasis_hotel.oasis_hotel.service.impl;

import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.entity.Room;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.mapper.RoomMapper;
import com.oasis_hotel.oasis_hotel.repository.HotelRepository;
import com.oasis_hotel.oasis_hotel.repository.RoomRepository;
import com.oasis_hotel.oasis_hotel.service.RoomService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



@Transactional
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository; 
    private final RoomMapper roomMapper;
    @Override
    public RoomResponseDTO createRoom(RoomRequestDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.hotelId())
                        .orElseThrow(()->new RuntimeException("Hotel not found with id:"+ dto.hotelId()));
        Room roomToSave = roomMapper.toEntity(dto);
        roomToSave.setHotel(hotel);
        roomToSave.setRoomStatus(RoomStatus.AVAILABLE);
        Room savedRoom = roomRepository.save(roomToSave);
        return roomMapper.toResponse(savedRoom);


        
    }


    
    
}
