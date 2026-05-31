package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis_hotel.oasis_hotel.entity.User;

public interface userRepository extends JpaRepository<User, Long>{
    
}
