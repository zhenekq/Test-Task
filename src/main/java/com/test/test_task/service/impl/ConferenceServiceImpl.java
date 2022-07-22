package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import com.test.test_task.exception.variants.BusinessLogicException;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.ConferenceService;
import com.test.test_task.util.ExceptionMessageStorage;
import com.test.test_task.validation.EntityValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final EntityValidation<Conference> conferenceEntityValidation;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, EntityConverter<Conference, ConferenceDto> conferenceConverter, RoomRepository roomRepository, ParticipantRepository participantRepository, EntityValidation<Conference> conferenceEntityValidation) {
        this.conferenceRepository = conferenceRepository;
        this.conferenceConverter = conferenceConverter;
        this.roomRepository = roomRepository;
        this.participantRepository = participantRepository;
        this.conferenceEntityValidation = conferenceEntityValidation;
    }

    @Override
    public ConferenceDto create(Conference conference, Long roomId) {
        if(!conferenceEntityValidation.isValid(conference)){
            throw new BusinessLogicException(ExceptionMessageStorage.conferenceNotValid(), HttpStatus.BAD_REQUEST);
        }
        List<Conference> conferences = conferenceRepository.findAllByRoomId(roomId);
        if(conferenceRepository.findByName(conference.getName()) != null){
            throw new BusinessLogicException(ExceptionMessageStorage.conferenceExists(conference.getName()), HttpStatus.CONFLICT);
        }
        if(conferences.size() >= 1) {
            throw new BusinessLogicException(ExceptionMessageStorage.roomIsOccupied(roomId), HttpStatus.BAD_REQUEST);
        }
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.roomNotExists(roomId), HttpStatus.NOT_FOUND));
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
                .orElseThrow(() -> new BusinessLogicException(ExceptionMessageStorage.conferenceNotExists(conferenceId), HttpStatus.NOT_FOUND));
        List<Participant> participants = conference.getParticipants();
        participants.forEach(el -> el.setConference(null));
        participantRepository.saveAll(participants);
        return conferenceConverter.convertToDto(conference);
    }
}
