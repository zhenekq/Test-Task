package com.test.test_task.service;

import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Room;

/**
 * Service layer for validation and business logic for RoomRepository
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.repositoty.RoomRepository
 */

public interface RoomService {

    /**
     * @param roomId of current room
     * @return is Conference available for new participants
     * @see com.test.test_task.service.impl.ConferenceServiceImpl
     */
    Boolean isAvailableById(Long roomId);

    /**
     * @param room related room
     * @return Room created for conference
     * @see com.test.test_task.service.impl.ConferenceServiceImpl
     */
    RoomDto createRoom(Room room);
}
