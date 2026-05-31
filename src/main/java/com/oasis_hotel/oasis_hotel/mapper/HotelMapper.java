package com.oasis_hotel.oasis_hotel.mapper;

import org.springframework.stereotype.Component;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;

@Component
public class HotelMapper {

    public HotelResponseDTO toResponse(Hotel hotel){
        return new HotelResponseDTO(
            hotel.getId(),
            hotel.getName(),
            hotel.getAddress(),
            hotel.getCity(),
            hotel.getStars(),
            hotel.getStatus()
        );
    }

    public Hotel toEntity(HotelRequestDTO dto){
        if(dto==null){
            return null;
        }

        Hotel hotel = new Hotel();
        hotel.setName(dto.name());
        hotel.setAddress(dto.address());
        hotel.setCity(dto.city());
        hotel.setStars(dto.stars());
        return hotel;
    }

}
