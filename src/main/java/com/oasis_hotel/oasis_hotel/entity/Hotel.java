package com.oasis_hotel.oasis_hotel.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(nullable= false, length=100)
    private String name;

    @Column(nullable= false, length=255)
    private String address;

    @Column(nullable=false , length = 100)
    private String city;

    @Column(name="stars_rating")
    private Integer stars;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at" ,nullable=false)
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

}
