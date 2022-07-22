package com.test.test_task.validation.impl;

import com.test.test_task.entity.Room;
import com.test.test_task.validation.EntityValidation;
import org.springframework.stereotype.Component;

@Component
public class RoomValidationImpl implements EntityValidation<Room> {
    @Override
    public Boolean isValid(Room room) {
        return room.getMaxSeats() >= 1;
    }
}
