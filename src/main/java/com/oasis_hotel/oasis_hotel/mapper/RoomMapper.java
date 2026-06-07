package com.oasis_hotel.oasis_hotel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.oasis_hotel.oasis_hotel.dto.room.RoomRequestDTO;
import com.oasis_hotel.oasis_hotel.dto.room.RoomResponseDTO;
import com.oasis_hotel.oasis_hotel.entity.Room;


//   'unmappedTargetPolicy = IGNORE' suppresses yellow warnings for fields that are not mapped 
//    (like auto-generated IDs, dates, or the hotel object that we will set manually).
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    //room.getHotel().getId() -> dto.setHotelId()
    @Mapping(source = "hotel.id", target = "hotelId")

    //room.getHotel().getName() -> dto.setHotelName()
    @Mapping(source = "hotel.name", target = "hotelName")
    RoomResponseDTO toResponse(Room room);

    // Since MapStruct cannot fetch data from the database, we tell it to ignore the 'hotel' field.
    @Mapping(target = "hotel", ignore = true)
    Room toEntity(RoomRequestDTO request);
}                                                       
