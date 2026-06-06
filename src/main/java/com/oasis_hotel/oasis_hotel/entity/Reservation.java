package com.oasis_hotel.oasis_hotel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

    @Column(name="check_in_date", nullable=false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable=false)
    private LocalDate checkOutDate;

    @Column(name="number_of_guests", nullable=false)
    private Integer numberOfGuests;

    @Column(name="total_price", nullable=false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name="status" , nullable=false)
    private ReservationStatus status;

    @Column(name="created_at" , nullable=false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    


}
