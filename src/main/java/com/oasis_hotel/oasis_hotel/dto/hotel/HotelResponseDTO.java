package com.oasis_hotel.oasis_hotel.dto.hotel;

public record HotelResponseDTO(
    Long id,
    String name,
    String address,
    String city,
    Integer stars,
    Boolean isActive
) {
    
}
