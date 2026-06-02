package com.oasis_hotel.oasis_hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oasis_hotel.oasis_hotel.dto.user.UserResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM Users u WHERE LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%' , :name , '%'))")
    Page<UserResponseDTO> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
    
}
