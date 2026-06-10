package com.oasis_hotel.oasis_hotel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oasis_hotel.oasis_hotel.dto.hotel.HotelRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelResponseDTO;
import com.oasis_hotel.oasis_hotel.dto.hotel.HotelSetStatusRequestDTO;
import com.oasis_hotel.oasis_hotel.service.HotelService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/hotels")
public class HotelController {

    private final HotelService hotelService;


    @GetMapping
    public ResponseEntity<Page<HotelResponseDTO>> getAllHotel( @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        Page<HotelResponseDTO> response = hotelService.getAllHotels(pageable);
        return ResponseEntity.ok(response);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        HotelResponseDTO response = hotelService.getHotelById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/name")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelsByName(@RequestParam String name,@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        
        Page<HotelResponseDTO> response = hotelService.getHotelByName(name, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/city")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelByCity(@RequestParam String city, @PageableDefault(size=10, page=0, sort="id") Pageable pageable) {
        Page<HotelResponseDTO> response = hotelService.getHotelByCity(city, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("search/address")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelByAddress(@RequestParam String address, @PageableDefault(size=10, page=0, sort="id") Pageable pageable) {
        Page<HotelResponseDTO> response = hotelService.getHotelByAddress(address, pageable);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("search/goodRatedHotels")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelGooRated(@PageableDefault(size=10,page=0, sort="id") Pageable pageable) {
        
        Page<HotelResponseDTO> response = hotelService.getHotelsGoodRated(pageable);

        return ResponseEntity.ok(response);
        
    }
    
    @GetMapping("search/stars")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelByStars(@RequestParam Integer stars, @PageableDefault(size=10, page=0, sort="id") Pageable pageable) {
        Page<HotelResponseDTO> response = hotelService.getHotelByStars(stars, pageable);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("search/recently-released")
    public ResponseEntity<Page<HotelResponseDTO>> getRecentlyReleasedHotel(@PageableDefault(size=10, page=0, sort="id") Pageable pageable) {
        Page<HotelResponseDTO> response = hotelService.getHotelRecentlyReleased(pageable);
        return ResponseEntity.ok(response);
    }
    
    
    

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@Valid @RequestBody HotelRequestDTO request) {
        HotelResponseDTO newHotel = hotelService.createHotel(request);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequestDTO request) {
        HotelResponseDTO hotelUpdated = hotelService.updateHotel(id, request);
        
        return ResponseEntity.ok(hotelUpdated);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/status/{id}")
    public ResponseEntity<HotelResponseDTO> setHotelStatus(@PathVariable Long id,@Valid @RequestBody  HotelSetStatusRequestDTO request) {
        HotelResponseDTO response = hotelService.setHotelStatus(id, request);
        
        return ResponseEntity.ok(response);
    }
    
}