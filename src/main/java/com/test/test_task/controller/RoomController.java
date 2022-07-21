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
@RequestMapping("/api/v1/conference/{conferenceId}/room")
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
     * @param conferenceId needed conference
     * @param roomDto room info
     * @return room
     */
    @PostMapping
    public RoomDto createRoom(@PathVariable Long conferenceId,
                              @RequestBody RoomDto roomDto){
        Room room = converter.convert(roomDto);

        return roomService.createRoom(conferenceId, room);
    }

    /**
     * GET request to get availability of conference's room
     *
     * @param conferenceId needed conference
     * @param roomId needed room
     * @return TRUE / FALSE
     */
    @GetMapping("{roomId}/available")
    public Boolean isAvailable(@PathVariable Long conferenceId,
                               @PathVariable Long roomId){
        return roomService.isAvailableById(conferenceId, roomId);
    }

}
