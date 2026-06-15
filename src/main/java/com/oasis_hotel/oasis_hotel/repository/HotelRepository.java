package com.oasis_hotel.oasis_hotel.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;

        public interface HotelRepository extends JpaRepository<Hotel, Long>{
        
        //SELECT * FROM hotels WHERE UPPER(name) LIKE UPPER('%oasis%');
        Page<Hotel> findByNameContainingIgnoreCase(String name, Pageable pageable);
        Page<Hotel> findByCityContainingIgnoreCase(String city, Pageable pageable);
        Page<Hotel> findByAddressContainingIgnoreCase(String address, Pageable pageable);
        Page<Hotel> findByStarsGreaterThan(Integer stars , Pageable pageable);
        Page<Hotel> findByStars(Integer stars, Pageable pageable);
        Page<Hotel> findByCreatedAtGreaterThanEqual(LocalDateTime dateTime, Pageable pageable);

/**
     * INTEGRATED SEARCH ALGORITHM. (Next.js Multi-Filter Hero Component Mapping)
     * Queries hotels by city destination with active availability filters that compute capacity
     * boundaries alongside reservation time-window intersection overlaps dynamically
     */

        @Query( "SELECT DISTINCT h FROM Hotel h JOIN h.rooms r " +
                "WHERE (:city IS NULL OR :city = '' OR LOWER(h.city) LIKE LOWER(CONCAT('%', :city, '%'))) " +
                "AND r.capacity >= :guests " +
                "AND h.status = 'ACTIVE' " +
                "AND r.roomStatus = :statusRoom " +
                "AND r.id NOT IN (" +
                "   SELECT res.room.id FROM Reservation res " +
                "   WHERE res.status != :statusRes " + 
                "   AND res.checkInDate < :checkOutDate AND res.checkOutDate > :checkInDate" +
                ")")
        Page<Hotel> findAvailableHotels(
                @Param("city") String city, 
                @Param("guests") Integer guests, 
                @Param("checkInDate") LocalDate checkInDate, 
                @Param("checkOutDate") LocalDate checkOutDate, 
                @Param("statusRoom") RoomStatus statusRoom,       
                @Param("statusRes") ReservationStatus statusRes,  
                Pageable pageable
        );

}
