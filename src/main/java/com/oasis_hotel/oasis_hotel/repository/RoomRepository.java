package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis_hotel.oasis_hotel.entity.Room;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;



public interface RoomRepository extends JpaRepository<Room,Long> {
    Page<Room> findByHotelId(Long hotelId, Pageable pageable);
    Page<Room> findByCapacity(Integer capacity, Pageable pageable);
    Page<Room> findByRoomStatus(RoomStatus roomStatus, Pageable pageable);
    Page<Room> findByRoomType(RoomType type, Pageable pageable);
    
}
