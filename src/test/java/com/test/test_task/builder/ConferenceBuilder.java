package com.test.test_task.builder;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;

import java.time.LocalDateTime;
import java.util.List;

public class ConferenceBuilder {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Participant> participants;
    private Room room;

    public ConferenceBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ConferenceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConferenceBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public ConferenceBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public ConferenceBuilder setParticipants(List<Participant> participants) {
        this.participants = participants;
        return this;
    }

    public ConferenceBuilder setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Conference createConference() {
        return new Conference(id, name, startDate, endDate, participants, room);
    }
}