package com.test.test_task.controller;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Room;
import com.test.test_task.service.RoomService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that process Room Logic
 * @see RoomService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;
    private final EntityConverter<Room, RoomDto> converter;

    public RoomController(RoomService roomService, EntityConverter<Room, RoomDto> converter) {
        this.roomService = roomService;
        this.converter = converter;
    }

    /**
     * POST request to create room
     *
     * @param roomDto room info
     * @return room
     */
    @PostMapping
    public RoomDto createRoom(@RequestBody RoomDto roomDto){
        Room room = converter.convert(roomDto);

        return roomService.createRoom(room);
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
