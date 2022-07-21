package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.RoomService;
import com.test.test_task.util.ExceptionMessageStorage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ConferenceRepository conferenceRepository;
    private final ParticipantRepository participantRepository;
    private final EntityConverter<Room, RoomDto> converter;

    public RoomServiceImpl(RoomRepository roomRepository, ConferenceRepository conferenceRepository, ParticipantRepository participantRepository, EntityConverter<Room, RoomDto> converter) {
        this.roomRepository = roomRepository;
        this.conferenceRepository = conferenceRepository;
        this.participantRepository = participantRepository;
        this.converter = converter;
    }

    @Override
    public Boolean isAvailableById(Long roomId) {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.roomNotExists(roomId)));
        Conference conference = conferenceRepository.findByRoomId(roomId);
        if(conference == null){
            return true;
        }
        if(participantRepository.countParticipantsByConferenceId(conference.getId()) == null){
            return true;
        }
        if(room.getMaxSeats().equals(participantRepository.countParticipantsByConferenceId(conference.getId()))){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public RoomDto createRoom(Room room) {
        Room newRoom =  roomRepository.save(room);
        return converter.convertToDto(newRoom);
    }
}
