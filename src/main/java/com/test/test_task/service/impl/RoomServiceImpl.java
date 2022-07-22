package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.RoomDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import com.test.test_task.exception.variants.BusinessLogicException;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.repositoty.ParticipantRepository;
import com.test.test_task.repositoty.RoomRepository;
import com.test.test_task.service.RoomService;
import com.test.test_task.util.ExceptionMessageStorage;
import com.test.test_task.validation.EntityValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ConferenceRepository conferenceRepository;
    private final ParticipantRepository participantRepository;
    private final EntityConverter<Room, RoomDto> converter;
    private final EntityValidation<Room> roomEntityValidation;

    public RoomServiceImpl(RoomRepository roomRepository, ConferenceRepository conferenceRepository, ParticipantRepository participantRepository, EntityConverter<Room, RoomDto> converter, EntityValidation<Room> roomEntityValidation) {
        this.roomRepository = roomRepository;
        this.conferenceRepository = conferenceRepository;
        this.participantRepository = participantRepository;
        this.converter = converter;
        this.roomEntityValidation = roomEntityValidation;
    }

    @Override
    public Boolean isAvailableById(Long roomId) {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() ->  new BusinessLogicException(ExceptionMessageStorage.roomNotExists(roomId), HttpStatus.NOT_FOUND));
        Conference conference = conferenceRepository.findByRoomId(roomId);

        Long amountOfParticipants = participantRepository.countParticipantsByConferenceId(conference.getId());
        if(conference == null){
            return true;
        }
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
    public RoomDto createRoom(Room room) {
        if(!roomEntityValidation.isValid(room)){
            throw new BusinessLogicException(ExceptionMessageStorage.roomIsNotValid(), HttpStatus.BAD_REQUEST);
        }
        Room newRoom =  roomRepository.save(room);
        return converter.convertToDto(newRoom);
    }
}
