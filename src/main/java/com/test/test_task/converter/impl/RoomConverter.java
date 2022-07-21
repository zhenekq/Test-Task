package com.test.test_task.converter.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for Room
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see EntityConverter
 */

@Component
public class RoomConverter implements EntityConverter<Room, RoomDto> {
    @Override
    public RoomDto convertToDto(Room room) {
        RoomDto dto = new RoomDto();
        dto.setIsAvailable(room.getIsAvailable());
        dto.setId(room.getId());
        dto.setMaxSeats(room.getMaxSeats());

        return dto;
    }

    @Override
    public List<RoomDto> convertToListDto(List<Room> rooms) {
        return rooms
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Room convert(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setIsAvailable(roomDto.getIsAvailable());
        room.setMaxSeats(roomDto.getMaxSeats());

        return room;
    }

    @Override
    public List<Room> convertToList(List<RoomDto> roomDtos) {
        return roomDtos
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
