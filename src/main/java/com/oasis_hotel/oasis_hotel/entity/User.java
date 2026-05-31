package com.oasis_hotel.oasis_hotel.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.oasis_hotel.oasis_hotel.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable=false, length=30)
    private String firstName;

    @Column(name="last_name", nullable=false, length=30)
    private String lastName;

    @Column(nullable=false,unique=true,length=100)
    private String email;

    @Column(nullable=false, length=100)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role = Role.CUSTOMER;

    @CreationTimestamp
    @Column(name="created_at",nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updatedAt",nullable=false)
    private LocalDateTime updatedAt;
}
