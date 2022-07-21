package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.ConferenceService;
import com.test.test_task.util.ExceptionMessageStorage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ConferenceService
 *
 * @author Eugene
 * @version 1.0
 * @see ConferenceService
 */

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final EntityConverter<Conference, ConferenceDto> conferenceConverter;
    private final RoomRepository roomRepository;
    private final ParticipantRepository participantRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, EntityConverter<Conference, ConferenceDto> conferenceConverter, RoomRepository roomRepository, ParticipantRepository participantRepository) {
        this.conferenceRepository = conferenceRepository;
        this.conferenceConverter = conferenceConverter;
        this.roomRepository = roomRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public ConferenceDto create(Conference conference, Long roomId) {
        List<Conference> conferences = conferenceRepository.findAllByRoomId(roomId);
        if(conferences.size() >= 1){
            throw new RuntimeException("This room is already taken");
        }
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.roomNotExists(roomId)));
        if(conferenceRepository.findByName(conference.getName()) != null){
            throw new RuntimeException("This Conference is already exists!");
        }
        conference.setStartDate(LocalDateTime.now());
        conference.setEndDate(LocalDateTime.now());
        conference.setRoom(room);
        Conference newConference = conferenceRepository.save(conference);
        return conferenceConverter.convertToDto(newConference);
    }


    @Override
    public ConferenceDto cancelById(Long conferenceId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.conferenceNotExists(conferenceId)));
        List<Participant> participants = conference.getParticipants();
        participants.forEach(el -> el.setConference(null));
        participantRepository.saveAll(participants);
        return conferenceConverter.convertToDto(conference);
    }
}
