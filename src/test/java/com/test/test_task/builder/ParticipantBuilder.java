package com.test.test_task.builder;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;

import java.util.List;

public class ParticipantBuilder {
    private Long id;
    private String username;
    private List<Conference> conference;

    public ParticipantBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ParticipantBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ParticipantBuilder setConference(List<Conference> conference) {
        this.conference = conference;
        return this;
    }

    public Participant createParticipant() {
        return new Participant(id, username, conference);
    }
}