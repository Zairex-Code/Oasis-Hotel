package com.oasis_hotel.oasis_hotel.dto.room;

import java.math.BigDecimal;

import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomType;

public record RoomResponseDTO(

    Long id,
    String roomNumber,
    Integer capacity,
    BigDecimal pricePerNight,
    RoomType roomType,
    RoomStatus roomStatus,
    Long hotelId,
    String hotelName
) {
}
