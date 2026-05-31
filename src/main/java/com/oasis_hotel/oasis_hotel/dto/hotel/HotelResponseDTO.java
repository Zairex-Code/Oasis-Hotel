package com.oasis_hotel.oasis_hotel.dto.hotel;

import com.oasis_hotel.oasis_hotel.entity.enums.HotelStatus;

public record HotelResponseDTO(
    Long id,
    String name,
    String address,
    String city,
    Integer stars,
    HotelStatus status
) {
    
}
