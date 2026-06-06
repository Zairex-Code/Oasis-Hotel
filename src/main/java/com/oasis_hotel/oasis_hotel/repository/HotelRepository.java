package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis_hotel.oasis_hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
    
    //SELECT * FROM hotels WHERE UPPER(name) LIKE UPPER('%oasis%');
    Page<Hotel> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Hotel> findByCityContainingIgnoreCase(String city, Pageable pageable);
    Page<Hotel> findByAddressContainingIgnoreCase(String address, Pageable pageable);
    Page<Hotel> findByStarsGreaterThan(Integer stars , Pageable pageable);
    Page<Hotel> findByStars(Integer stars, Pageable pageable);
    
}
