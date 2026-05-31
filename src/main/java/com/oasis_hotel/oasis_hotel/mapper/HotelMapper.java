package com.oasis_hotel.oasis_hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING )
public interface  HotelMapper {
    HotelResponseDTO toResponse(Hotel hotel);
    Hotel toEntity(HotelRequestDTO dto);
}
