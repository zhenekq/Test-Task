package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.service.ParticipantService;
import com.test.test_task.util.ExceptionStorage;
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

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ConferenceRepository conferenceRepository, EntityConverter<Participant, ParticipantDto> converter) {
        this.participantRepository = participantRepository;
        this.conferenceRepository = conferenceRepository;
        this.converter = converter;
    }

    @Override
    public ParticipantDto addToConference(Participant participant, Long conferenceId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionStorage.conferenceNotExists(conferenceId)));

        if(!conference.getIsAvailable()){
            throw new RuntimeException(ExceptionStorage.conferenceUnavailable(conferenceId));
        }
        if(participantRepository.countParticipantsByConferenceId(conferenceId) - 1 < (conference.getMaxSeats())){
            conference.setIsAvailable(Boolean.FALSE);
            conferenceRepository.save(conference);
        }
        participant.setConference(conference);
        Participant newParticipant = participantRepository.save(participant);

        return converter.convertToDto(newParticipant);
    }

    @Override
    public ParticipantDto deleteById(Long conferenceId, Long participantId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionStorage.conferenceNotExists(conferenceId)));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionStorage.participantNotExists(conferenceId)));
        if(!participant.getConference().getId().equals(conferenceId)){
            throw new RuntimeException(ExceptionStorage.participantNotIncluded(participantId));
        }
        if(participantRepository.countParticipantsByConferenceId(conferenceId) - 1 < (conference.getMaxSeats())){
            conference.setIsAvailable(Boolean.TRUE);
            conferenceRepository.save(conference);
        }

        participantRepository.deleteById(participantId);
        return converter.convertToDto(participant);
    }
}
