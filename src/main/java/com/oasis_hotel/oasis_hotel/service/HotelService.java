package com.oasis_hotel.oasis_hotel.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelSetStatusRequestDTO;

public interface HotelService{
    HotelResponseDTO createHotel(HotelRequestDTO dto);
    Page<HotelResponseDTO> getAllHotels(Pageable pageable);
    HotelResponseDTO getHotelById(Long id);
    Page<HotelResponseDTO> getHotelByName(String name, Pageable pageable);
    Page<HotelResponseDTO> getHotelByCity(String city, Pageable pageable);
    Page<HotelResponseDTO> getHotelByAddress(String address, Pageable pageable);
    Page<HotelResponseDTO> getHotelsGoodRated(Pageable pageable);
    Page<HotelResponseDTO> getHotelByStars(Integer stars, Pageable pageable);
    Page<HotelResponseDTO>getHotelRecentlyReleased(Pageable pageable);
    HotelResponseDTO updateHotel(Long id, HotelRequestDTO request);
    HotelResponseDTO setHotelStatus(Long id, HotelSetStatusRequestDTO request);
    

}