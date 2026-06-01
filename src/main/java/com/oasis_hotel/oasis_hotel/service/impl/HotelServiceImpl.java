package com.oasis_hotel.oasis_hotel.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.mapper.HotelMapper;
import com.oasis_hotel.oasis_hotel.repository.HotelRepository;
import com.oasis_hotel.oasis_hotel.service.HotelService;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository , HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    

    @Override
    public HotelResponseDTO createHotel(HotelRequestDTO request){
        Hotel hotelToSave = hotelMapper.toEntity(request);
        Hotel hotelSaved = hotelRepository.save(hotelToSave);
        return hotelMapper.toResponse(hotelSaved);


        
    }



    @Override
    public Page<HotelResponseDTO> getAllHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable)
                        .map(hotelMapper::toResponse);
        
    }



}
