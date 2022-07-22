package com.test.test_task.service;

import com.test.test_task.builder.ConferenceBuilder;
import com.test.test_task.builder.ParticipantBuilder;
import com.test.test_task.builder.RoomBuilder;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
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
public class RoomServiceTest {

    private final ParticipantService service;
    private final RoomService roomService;
    private final ConferenceService conferenceService;

    @Autowired
    public RoomServiceTest(ParticipantService service, RoomService roomService, ConferenceService conferenceService) {
        this.service = service;
        this.roomService = roomService;
        this.conferenceService = conferenceService;
    }

    @Test
    @DisplayName("Create room with valid data")
    void createNewConference(){
        Room room = new RoomBuilder().plain().createRoom();
        Room createdRoom = roomService.createRoom(room);

        assertEquals(createdRoom.getMaxSeats(), room.getMaxSeats());
    }

    @Test
    @DisplayName("Check is room unavailable")
    void checkIsRoomAvailableFalse(){
        Room room = new RoomBuilder().plain().createRoom();
        Room createdRoom = roomService.createRoom(room);

        Conference createdConference = getConferenceDto(room, createdRoom, RandomString.make());
        Participant first = getParticipantDto(RandomString.make());
        Participant second = getParticipantDto(RandomString.make());

        service.addToConference(first.getId(), createdConference.getId());
        service.addToConference(second.getId(), createdConference.getId());

        Boolean available = roomService.isAvailableById(createdRoom.getId());

        assertEquals(Boolean.FALSE, available);
    }

    @Test
    @DisplayName("Check is room available")
    void checkIsRoomAvailableTrue(){
        Room room = new RoomBuilder().plain().createRoom();
        Room createdRoom = roomService.createRoom(room);

        Conference createdConference = getConferenceDto(room, createdRoom, RandomString.make());
        Participant first = getParticipantDto(RandomString.make());

        service.addToConference(first.getId(), createdConference.getId());

        Boolean available = roomService.isAvailableById(createdRoom.getId());

        assertEquals(Boolean.TRUE, available);
    }

    private Conference getConferenceDto(Room room, Room createdRoom, String name) {
        Conference conference = new ConferenceBuilder().setRoom(room).setName(name).createConference();
        Conference createdConference = conferenceService.create(conference, createdRoom.getId());
        return createdConference;
    }

    private Participant getParticipantDto(String username) {
        Participant participant = new ParticipantBuilder().setUsername(username).createParticipant();
        Participant createdParticipant = service.create(participant);
        return createdParticipant;
    }
}
