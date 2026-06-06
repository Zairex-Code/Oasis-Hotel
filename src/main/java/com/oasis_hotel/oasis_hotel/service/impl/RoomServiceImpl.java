package com.oasis_hotel.oasis_hotel.service.impl;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomUpdateRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.entity.Room;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;
import com.oasis_hotel.oasis_hotel.exception.ResourceNotFoundException;
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
                        .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id:"+ dto.hotelId()));
        Room roomToSave = roomMapper.toEntity(dto);
        roomToSave.setHotel(hotel);
        roomToSave.setRoomStatus(RoomStatus.AVAILABLE);
        Room savedRoom = roomRepository.save(roomToSave);
        return roomMapper.toResponse(savedRoom);
        
        
        
    }
    @Override
    public Page<RoomResponseDTO> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable).map(roomMapper::toResponse);
    }
    @Override
    public RoomResponseDTO getRoomById(Long id) {
        Room room =roomRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Room not found with id: " + id));
        return roomMapper.toResponse(room);
    }
    @Override
    public Page<RoomResponseDTO> getRoomByHotelId(Long hotelId, Pageable pageable) {
        
        Page<Room> room = roomRepository.findByHotelId(hotelId, pageable);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not found with hotel id: " +hotelId);
        }
        return room.map(roomMapper::toResponse);
    }

    
    @Override
    public Page<RoomResponseDTO> getRoomByCapacity(Integer capacity, Pageable pageable) {
        
        Page<Room> room = roomRepository.findByCapacity(capacity, pageable);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not found with capacity for " + capacity );
        }

        return room.map(roomMapper::toResponse);
    }
    @Override
    public Page<RoomResponseDTO> getRoomByStatus(RoomStatus status, Pageable pageable) {
        
        Page<Room> room = roomRepository.findByRoomStatus(status, pageable);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not found with status: " + status);
        }

        return room.map(roomMapper::toResponse);
        
    }
    @Override
    public Page<RoomResponseDTO> getRoomByType(RoomType type, Pageable pageable) {
        

        Page<Room> room = roomRepository.findByRoomType(type, pageable);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not foundd with type " + type);
        }
        return room.map(roomMapper::toResponse);
    }
    
    @Override
    public Page<RoomResponseDTO> getRoomByPriceRange(Long hotelId,BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        

        Page<Room> room = roomRepository.findByHotelIdAndPricePerNightBetween(hotelId, minPrice, maxPrice, pageable);

        if(room.isEmpty()){
            throw new ResourceNotFoundException("Room not found with that range");
        }
        return room.map(roomMapper::toResponse);
    }
    @Override
    public RoomResponseDTO updateRoom(Long id, RoomUpdateRequestDTO request) {
        Room room = roomRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Room not found with id : " + id));
        room.setRoomNumber(request.roomNumber());
        room.setCapacity(request.capacity());
        room.setPricePerNight(request.pricePerNight());
        room.setRoomType(request.roomType());
        room.setRoomStatus(request.roomStatus());

        Room roomUpdated = roomRepository.save(room);
        return roomMapper.toResponse(roomUpdated);
    }


    
    
}
