package com.oasis_hotel.oasis_hotel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oasis_hotel.oasis_hotel.entity.enums.HotelStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita ciclos infinitos al enviar la respuesta al frontend
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private HotelStatus status = HotelStatus.ACTIVE;

    @Column(name="created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private  LocalDateTime updatedAt;

}
