package com.oasis_hotel.oasis_hotel.service;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;

public interface HotelService{
    HotelResponseDTO createHotel(HotelRequestDTO dto);

}