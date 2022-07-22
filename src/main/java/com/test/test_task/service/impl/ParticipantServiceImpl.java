package com.test.test_task.service.impl;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import com.test.test_task.entity.Room;
import com.test.test_task.exception.variants.BusinessLogicException;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.ParticipantService;
import com.test.test_task.util.ExceptionMessageStorage;
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
    private final RoomRepository roomRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ConferenceRepository conferenceRepository, RoomRepository roomRepository) {
        this.participantRepository = participantRepository;
        this.conferenceRepository = conferenceRepository;
        this.roomRepository = roomRepository;
    }



    @Override
    public Participant addToConference(Long participantId, Long conferenceId) {
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

        return participantRepository.save(participant);
    }

    @Override
    public Participant deleteById(Long conferenceId, Long participantId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.conferenceNotExists(conferenceId), HttpStatus.NOT_FOUND));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.participantNotExists(conferenceId), HttpStatus.NOT_FOUND));

        List<Conference> conferences = participant.getConference();
        conferences.remove(conference);

        participantRepository.save(participant);

        return participant;
    }

    @Override
    public Participant create(Participant participant) {

        Optional<Participant> another = participantRepository.findByUsername(participant.getUsername());
        if(another.isPresent()){
            throw new BusinessLogicException(ExceptionMessageStorage.participantExists(participant.getUsername()), HttpStatus.BAD_REQUEST);
        }

        Participant newParticipant = participantRepository.save(participant);

        return newParticipant;
    }
}
