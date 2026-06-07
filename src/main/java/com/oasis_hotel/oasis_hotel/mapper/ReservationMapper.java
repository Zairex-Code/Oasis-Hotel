package com.oasis_hotel.oasis_hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.reservation.ReservationResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Reservation;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

    @Mapping(source="user.id" , target="userId")
    @Mapping(source="user.firstName", target="userFirstName")
    @Mapping(source="user.lastName", target="userLastName")
    @Mapping(source="user.email", target="userEmail")
    @Mapping(source="room.hotel.id", target="hotelId")
    @Mapping(source="room.hotel.name", target="hotelName")
    @Mapping(source="room.id", target="roomId")
    @Mapping(source="room.roomNumber", target="roomNumber")
    @Mapping(source="room.roomType", target="roomType")
    ReservationResponseDTO toResponse(Reservation reservation);

    @Mapping(target="id", ignore=true)
    @Mapping(target="user", ignore=true)
    @Mapping(target="room", ignore=true)
    @Mapping(target="totalPrice", ignore=true)
    @Mapping(target="status", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    Reservation toEntity(ReservationRequestDTO request);
    
}
