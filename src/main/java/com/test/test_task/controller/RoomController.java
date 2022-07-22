package com.test.test_task.controller;

import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Room;
import com.test.test_task.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller that process Room Logic
 * @see RoomService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/room")
@Validated
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;

    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    /**
     * POST request to create room
     *
     * @param roomDto room info
     * @return room
     */
    @PostMapping
    public RoomDto createRoom(@Valid @RequestBody RoomDto roomDto, Errors errors){
        Room room = modelMapper.map(roomDto, Room.class);
        Room newRoom = roomService.createRoom(room);
        return modelMapper.map(newRoom, RoomDto.class);
    }

    /**
     * GET request to get availability of conference's room
     *
     * @param roomId needed room
     * @return TRUE / FALSE
     */
    @GetMapping("{roomId}")
    public Boolean isAvailable(@PathVariable Long roomId){
        return roomService.isAvailableById(roomId);
    }

}
