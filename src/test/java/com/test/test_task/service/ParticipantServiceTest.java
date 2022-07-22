package com.test.test_task.service;

import com.test.test_task.builder.ConferenceBuilder;
import com.test.test_task.builder.ParticipantBuilder;
import com.test.test_task.builder.RoomBuilder;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext
public class ParticipantServiceTest {

    private final ParticipantService service;
    private final RoomService roomService;
    private final ConferenceService conferenceService;

    @Autowired
    public ParticipantServiceTest(ParticipantService service, RoomService roomService, ConferenceService conferenceService) {
        this.service = service;
        this.roomService = roomService;
        this.conferenceService = conferenceService;
    }

    @Test
    @DisplayName("Participant create with valid data")
    void addParticipantWithValidData(){
        Participant participant = new ParticipantBuilder().setUsername(RandomString.make()).createParticipant();
        ParticipantDto createdParticipant = service.create(participant);

        assertEquals(participant.getUsername(), createdParticipant.getUsername());
    }

    @Test
    @DisplayName("Attach Participant to conference")
    void attachParticipantToConference(){
        Room room = new RoomBuilder().plain().createRoom();
        RoomDto createdRoom = roomService.createRoom(room);

        Conference conference = new ConferenceBuilder().setRoom(room).setName(RandomString.make()).createConference();
        ConferenceDto createdConference = conferenceService.create(conference, createdRoom.getId());

        Participant participant = new ParticipantBuilder().setUsername(RandomString.make()).createParticipant();
        ParticipantDto createdParticipant = service.create(participant);

        ParticipantDto dto = service.addToConference(createdParticipant.getId(), createdConference.getId());

        assertEquals(createdParticipant.getId(), dto.getId());
        assertEquals(createdConference.getName(), conference.getName());
        assertEquals(createdRoom.getMaxSeats(), room.getMaxSeats());
    }

    @Test
    @DisplayName("Leave participant conference by id")
    void leaveParticipantConference(){
        Room room = new RoomBuilder().plain().createRoom();
        RoomDto createdRoom = roomService.createRoom(room);

        Conference conference = new ConferenceBuilder().setRoom(room).setName(RandomString.make()).createConference();
        ConferenceDto createdConference = conferenceService.create(conference, createdRoom.getId());

        Participant participant = new ParticipantBuilder().setUsername(RandomString.make()).createParticipant();
        ParticipantDto createdParticipant = service.create(participant);

        ParticipantDto dto = service.deleteById(createdConference.getId(), createdParticipant.getId());

        assertEquals(createdParticipant.getId(), dto.getId());
        assertEquals(createdConference.getName(), conference.getName());
        assertEquals(createdRoom.getMaxSeats(), room.getMaxSeats());
    }

}
