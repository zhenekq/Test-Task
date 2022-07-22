package com.test.test_task.service.impl;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import com.test.test_task.exception.variants.BusinessLogicException;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.RoomService;
import com.test.test_task.util.ExceptionMessageStorage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ConferenceRepository conferenceRepository;
    private final ParticipantRepository participantRepository;

    public RoomServiceImpl(RoomRepository roomRepository, ConferenceRepository conferenceRepository, ParticipantRepository participantRepository) {
        this.roomRepository = roomRepository;
        this.conferenceRepository = conferenceRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public Boolean isAvailableById(Long roomId) {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.roomNotExists(roomId), HttpStatus.NOT_FOUND));
        Conference conference = conferenceRepository.findByRoomId(roomId);
        if(conference == null){
            return true;
        }
        Long amountOfParticipants = participantRepository.countParticipantsByConferenceId(conference.getId());
        
        if(amountOfParticipants == null){
            return true;
        }
        if(room.getMaxSeats().equals(amountOfParticipants)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
