package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import com.test.test_task.exception.variants.BusinessLogicException;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.ParticipantService;
import com.test.test_task.util.ExceptionMessageStorage;
import com.test.test_task.validation.EntityValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ParticipantService
 *
 * @author Eugene
 * @version 1.0
 * @see ParticipantService
 */
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ConferenceRepository conferenceRepository;
    private final EntityConverter<Participant, ParticipantDto> converter;
    private final RoomRepository roomRepository;
    private final EntityValidation<Participant> participantEntityValidation;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ConferenceRepository conferenceRepository, EntityConverter<Participant, ParticipantDto> converter, RoomRepository roomRepository, EntityValidation<Participant> participantEntityValidation) {
        this.participantRepository = participantRepository;
        this.conferenceRepository = conferenceRepository;
        this.converter = converter;
        this.roomRepository = roomRepository;
        this.participantEntityValidation = participantEntityValidation;
    }



    @Override
    public ParticipantDto addToConference(Long participantId, Long conferenceId) {
        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionMessageStorage.roomNotExists(conferenceId), HttpStatus.NOT_FOUND));
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.conferenceNotExists(conferenceId), HttpStatus.NOT_FOUND));
        Room room = roomRepository
                .findByConferenceId(conferenceId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.roomNotAttached(conferenceId), HttpStatus.NOT_FOUND));

        if(participantRepository.countParticipantsByConferenceId(conferenceId).equals(room.getMaxSeats())){
            throw new BusinessLogicException(ExceptionMessageStorage.roomIsFull(room.getId()), HttpStatus.BAD_REQUEST);
        }

        List<Conference> conferences = participant.getConference();
        conferences.add(conference);
        participant.setConference(conferences);
        participantRepository.save(participant);
        return converter.convertToDto(participant);
    }

    @Override
    public ParticipantDto deleteById(Long conferenceId, Long participantId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.conferenceNotExists(conferenceId), HttpStatus.NOT_FOUND));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.participantNotExists(conferenceId), HttpStatus.NOT_FOUND));

        List<Conference> conferences = participant.getConference();
        conferences.remove(conference);

        participantRepository.save(participant);

        return converter.convertToDto(participant);
    }

    @Override
    public ParticipantDto create(Participant participant) {
        if(!participantEntityValidation.isValid(participant)){
            throw new BusinessLogicException(ExceptionMessageStorage.participantIsNotValid(), HttpStatus.BAD_REQUEST);
        }

        Optional<Participant> another = participantRepository.findByUsername(participant.getUsername());
        if(another.isPresent()){
            throw new BusinessLogicException(ExceptionMessageStorage.participantExists(participant.getUsername()), HttpStatus.BAD_REQUEST);
        }

        Participant newParticipant = participantRepository.save(participant);

        return converter.convertToDto(newParticipant);
    }
}
