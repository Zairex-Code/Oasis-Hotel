package com.oasis_hotel.oasis_hotel.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.oasis_hotel.oasis_hotel.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%' , :name , '%'))")
    Page<User> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    Optional<User> findByEmail(String email);
    
}
