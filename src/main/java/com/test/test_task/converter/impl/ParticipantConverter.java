package com.test.test_task.converter.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Participant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantConverter implements EntityConverter<Participant, ParticipantDto> {

    private final EntityConverter<Conference, ConferenceDto> converter;

    public ParticipantConverter(EntityConverter<Conference, ConferenceDto> converter) {
        this.converter = converter;
    }

    @Override
    public ParticipantDto convertToDto(Participant participant) {
        ParticipantDto dto = new ParticipantDto();
        dto.setId(participant.getId());
        dto.setUsername(participant.getUsername());

        return dto;
    }

    @Override
    public List<ParticipantDto> convertToListDto(List<Participant> participants) {
        return participants
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Participant convert(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setUsername(participantDto.getUsername());
        participant.setId(participantDto.getId());

        return participant;
    }

    @Override
    public List<Participant> convertToList(List<ParticipantDto> participantDtos) {
        return participantDtos
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
