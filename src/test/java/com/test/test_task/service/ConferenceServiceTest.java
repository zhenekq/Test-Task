package com.test.test_task.service;

import com.test.test_task.builder.ConferenceBuilder;
import com.test.test_task.builder.RoomBuilder;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext
public class ConferenceServiceTest {

    private final ParticipantService service;
    private final RoomService roomService;
    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceServiceTest(ParticipantService service, RoomService roomService, ConferenceService conferenceService) {
        this.service = service;
        this.roomService = roomService;
        this.conferenceService = conferenceService;
    }

    @Test
    @DisplayName("Create conference with valid data")
    void createNewConference(){
        Room room = new RoomBuilder().plain().createRoom();
        Room createdRoom = roomService.createRoom(room);

        Conference conference = new ConferenceBuilder().setRoom(room).setName(RandomString.make()).createConference();
        Conference createdConference = conferenceService.create(conference, createdRoom.getId());

        assertEquals(createdConference.getName(), conference.getName());
        assertEquals(createdRoom.getMaxSeats(), room.getMaxSeats());
    }

    @Test
    @DisplayName("Cancel conference by id")
    void cancelConference(){
        Room room = new RoomBuilder().plain().createRoom();
        Room createdRoom = roomService.createRoom(room);

        Conference conference = new ConferenceBuilder().setRoom(room).setName(RandomString.make()).createConference();
        Conference createdConference = conferenceService.create(conference, createdRoom.getId());

        Conference canceledConference = conferenceService.cancelById(createdConference.getId());

        assertEquals(canceledConference.getName(), createdConference.getName());
        assertEquals(createdRoom.getMaxSeats(), room.getMaxSeats());
    }

}
