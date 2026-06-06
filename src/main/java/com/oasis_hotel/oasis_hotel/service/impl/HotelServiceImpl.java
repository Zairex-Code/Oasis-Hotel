package com.oasis_hotel.oasis_hotel.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelSetStatusRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.exception.ResourceNotFoundException;
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
        return hotelRepository
                                .findAll(pageable)
                                .map(hotelMapper::toResponse);
    }



    @Override
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id: "+id));
        return hotelMapper.toResponse(hotel);
        
    }
    
    
    @Override
    public Page<HotelResponseDTO> getHotelByName(String name, Pageable pageable) {

        Page<Hotel> hotel = hotelRepository.findByNameContainingIgnoreCase(name, pageable); 

        if(hotel.isEmpty()){
            throw new  ResourceNotFoundException("Hotel not found with name: " + name);
        }

        return  hotel.map(hotelMapper::toResponse);
        
    }
    @Override
    public Page<HotelResponseDTO> getHotelByCity(String city, Pageable pageable) {
        // TODO get hotels by city
        Page<Hotel> hotel = hotelRepository.findByCityContainingIgnoreCase(city, pageable);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel not found with city: " + city);
        }

        return hotel.map(hotelMapper::toResponse);
    
    }


    @Override
    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO request) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id: " + id));
        
        // update all data
        hotel.setName(request.name());
        hotel.setAddress(request.address());
        hotel.setCity(request.city());
        hotel.setStars(request.stars());
        
        // save hotel
        Hotel updatedHotel = hotelRepository.save(hotel);
        
        return  hotelMapper.toResponse(updatedHotel);
    }



    @Override
    public HotelResponseDTO setHotelStatus(Long id, HotelSetStatusRequestDTO request) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel not found with id: " + id));

        hotel.setStatus(request.status());

        Hotel hotelUpdated = hotelRepository.save(hotel);

        return hotelMapper.toResponse(hotelUpdated);
    }






    @Override
    public Page<HotelResponseDTO> getHotelByAddress(String address, Pageable pageable) {
        // TODO get hotels by address
        throw new UnsupportedOperationException("Unimplemented method 'getHotelByAddress'");
    }



    @Override
    public Page<HotelResponseDTO> getHotelsGoodRated(Pageable pageable) {
        // TODO get hotels good rated
        throw new UnsupportedOperationException("Unimplemented method 'getHotelsGoodRated'");
    }



    @Override
    public Page<HotelResponseDTO> getHotelRecentlyReleased(Pageable pageable) {
        // TODO get hotels recently added
        throw new UnsupportedOperationException("Unimplemented method 'getHotelRecentlyReleased'");
    }



    @Override
    public Page<HotelResponseDTO> getHotelByStars(Integer stars, Pageable pageable) {
        // TODO get hotels by stars
        throw new UnsupportedOperationException("Unimplemented method 'getHotelByStars'");
    }





}
