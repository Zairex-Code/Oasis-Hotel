package com.oasis_hotel.oasis_hotel.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;

public interface HotelService{
    HotelResponseDTO createHotel(HotelRequestDTO dto);
    Page<HotelResponseDTO> getAllHotels(Pageable pageable);
    HotelResponseDTO getHotelById(Long id);
    

}