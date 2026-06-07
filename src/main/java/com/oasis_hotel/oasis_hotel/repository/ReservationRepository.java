package com.oasis_hotel.oasis_hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oasis_hotel.oasis_hotel.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long >{

        Page<Reservation> findById(Long id, Pageable pageable);
        Page<Reservation> findByUserId(Long userId, Pageable pageable);

        
        // Search for active bookings for that room where the date ranges conflict
        @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND r.status <> 'CANCELLED' " +
                "AND (:checkIn < r.checkOutDate AND :checkOut > r.checkInDate)")
        List<Reservation> findConflictingReservations(
                @Param("roomId") Long roomId, 
                @Param("checkIn") LocalDate checkIn, 
                @Param("checkOut") LocalDate checkOut
        );

        
}
