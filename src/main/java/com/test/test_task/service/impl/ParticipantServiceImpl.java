package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.ParticipantService;
import com.test.test_task.util.ExceptionMessageStorage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ConferenceRepository conferenceRepository, EntityConverter<Participant, ParticipantDto> converter, RoomRepository roomRepository) {
        this.participantRepository = participantRepository;
        this.conferenceRepository = conferenceRepository;
        this.converter = converter;
        this.roomRepository = roomRepository;
    }

    @Override
    public ParticipantDto addToConference(Participant participant, Long conferenceId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.conferenceNotExists(conferenceId)));
        Room room = roomRepository
                .findByConferenceId(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.roomNotExists(conferenceId)));

        Long amountParticipantsOnConference = participantRepository.countParticipantsByConferenceId(conferenceId);
        Long roomMax = room.getMaxSeats();
        if(amountParticipantsOnConference >= roomMax){
            throw new RuntimeException(ExceptionMessageStorage.roomIsFull(room.getId()));
        }

        participant.setConference(conference);
        Participant newParticipant = participantRepository.save(participant);

        return converter.convertToDto(newParticipant);
    }

    @Override
    public ParticipantDto deleteById(Long conferenceId, Long participantId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.conferenceNotExists(conferenceId)));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.participantNotExists(participantId)));
        if(!participant.getConference().getId().equals(conferenceId)){
            throw new RuntimeException(ExceptionMessageStorage.participantNotIncluded(participantId));
        }

        participantRepository.deleteById(participantId);

        return converter.convertToDto(participant);
    }
}
