package com.test.test_task.builder;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;

public class RoomBuilder {
    private Long id;
    private Long maxSeats;
    private Conference conference;

    public RoomBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setMaxSeats(Long maxSeats) {
        this.maxSeats = maxSeats;
        return this;
    }

    public RoomBuilder setConference(Conference conference) {
        this.conference = conference;
        return this;
    }

    public RoomBuilder plain(){
        this.setMaxSeats(2L);
        return this;
    }

    public Room createRoom() {
        return new Room(id, maxSeats, conference);
    }
}
