package com.oasis_hotel.oasis_hotel.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelSetStatusRequestDTO;
import com.oasis_hotel.oasis_hotel.entity.Hotel;
import com.oasis_hotel.oasis_hotel.entity.enums.ReservationStatus;
import com.oasis_hotel.oasis_hotel.entity.enums.RoomStatus;
import com.oasis_hotel.oasis_hotel.exception.ResourceNotFoundException;
import com.oasis_hotel.oasis_hotel.mapper.HotelMapper;
import com.oasis_hotel.oasis_hotel.repository.HotelRepository;
import com.oasis_hotel.oasis_hotel.service.HotelService;

import jakarta.transaction.Transactional;
import net.bytebuddy.implementation.bytecode.Throw;



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
        
        Page<Hotel> hotel = hotelRepository.findByCityContainingIgnoreCase(city, pageable);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel not found with city: " + city);
        }

        return hotel.map(hotelMapper::toResponse);
    
    }
    
    @Override
    public Page<HotelResponseDTO> getHotelByAddress(String address, Pageable pageable) {
        Page<Hotel> hotel = hotelRepository.findByAddressContainingIgnoreCase(address, pageable);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel not found with address: " + address);
        }

        return hotel.map(hotelMapper::toResponse);
    }
    @Override
    public Page<HotelResponseDTO> getHotelsGoodRated(Pageable pageable) {
        
        Integer minStarRule = 3;
        Page<Hotel> hotel = hotelRepository.findByStarsGreaterThan(minStarRule, pageable);
        
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel not found with " + minStarRule );
        }
        
        return hotel.map(hotelMapper::toResponse);
    }
    
    @Override
    public Page<HotelResponseDTO> getHotelByStars(Integer stars, Pageable pageable) {
        
        Page<Hotel> hotel = hotelRepository.findByStars(stars, pageable);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("hotel not found with " + stars + " stars");
        }

        return hotel.map(hotelMapper::toResponse);
        
    }

    @Override
    public Page<HotelResponseDTO> getHotelRecentlyReleased(Pageable pageable) {
        
        LocalDateTime recentlyDays = LocalDateTime.now().minusDays(15);
        Page<Hotel> hotel = hotelRepository.findByCreatedAtGreaterThanEqual(recentlyDays, pageable);

        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel Not Found Added Recently");
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
    public Page<HotelResponseDTO> searchAvailableHotels(String city, Integer guests, LocalDate checkInDate, LocalDate checkOutDate, Pageable pageable) {
        
        // 1. DEFENSIVE TEMPORAL VALIDATIONS
        // Prevents frontend logical bypasses (e.g., checkout before checkin or booking in the past)
        if (checkInDate != null && checkOutDate != null && !checkInDate.isBefore(checkOutDate)) {
            throw new IllegalArgumentException("La fecha de entrada debe ser estrictamente anterior a la fecha de salida.");
        }
        if (checkInDate != null && checkInDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de reserva no puede estar en el pasado.");
        }

        // 2. SMART FALLBACKS FOR OPTIONAL QUERY PARAMETERS
        // Sanitizes inputs and assigns coherent defaults if Next.js submits empty search fields
        String searchCity = (city != null && !city.trim().isEmpty()) ? city.trim() : null;
        int targetGuests = (guests != null && guests > 0) ? guests : 1;
        LocalDate targetCheckIn = (checkInDate != null) ? checkInDate: LocalDate.now();
        LocalDate targetCheckOut = (checkOutDate != null) ? checkOutDate: LocalDate.now();

        // 3. EXECUTE ADVANCE AVAILABILITY MATRIX ALGORITHM
        //We pass RoomStatus.AVAILABLE and ReservationStatus.CANCELLED cleanly as Java parameters
        Page<Hotel> hotels = hotelRepository.findAvailableHotels(
                                                                searchCity, 
                                                                targetGuests, 
                                                                targetCheckIn, 
                                                                targetCheckOut, 
                                                                RoomStatus.AVAILABLE, 
                                                                ReservationStatus.CANCELLED, 
                                                                pageable);
                                                                
        if(hotels.isEmpty()){
            throw new ResourceNotFoundException("Available Hotels not found");
        }
        return hotels.map(hotelMapper::toResponse);
    }




















}
