package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis_hotel.oasis_hotel.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
    
}
