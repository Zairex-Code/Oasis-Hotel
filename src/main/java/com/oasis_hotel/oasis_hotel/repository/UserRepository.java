package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    
    
}
