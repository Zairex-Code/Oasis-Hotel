package com.oasis_hotel.oasis_hotel.service;


import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;

public interface HotelService{
    HotelResponseDTO createHotel(HotelRequestDTO dto);
    Page<HotelResponseDTO> getAllHotels(Pageable pageable);

}