package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.RoomService;
import com.test.test_task.util.ExceptionMessageStorage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ConferenceRepository conferenceRepository;
    private final EntityConverter<Room, RoomDto> converter;

    public RoomServiceImpl(RoomRepository roomRepository, ConferenceRepository conferenceRepository, EntityConverter<Room, RoomDto> converter) {
        this.roomRepository = roomRepository;
        this.conferenceRepository = conferenceRepository;
        this.converter = converter;
    }

    @Override
    public Boolean isAvailableById(Long conferenceId, Long roomId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.conferenceNotExists(conferenceId)));
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.roomNotExists(conferenceId)));
        if(!room.getConference().getId().equals(conference.getId())){
            throw new EntityNotFoundException(ExceptionMessageStorage.roomIsNotRelated(roomId));
        }
        return room.getIsAvailable();
    }

    @Override
    public RoomDto createRoom(Long conferenceId, Room room) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageStorage.conferenceNotExists(conferenceId)));
        room.setConference(conference);
        room.setIsAvailable(Boolean.TRUE);
        Room newRoom =  roomRepository.save(room);

        return converter.convertToDto(newRoom);
    }
}
