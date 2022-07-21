package com.test.test_task.converter.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConferenceConverter implements EntityConverter<Conference, ConferenceDto> {
    @Override
    public ConferenceDto convertToDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setId(conference.getId());
        dto.setIsAvailable(conference.getIsAvailable());
        dto.setMaxSeats(conference.getMaxSeats());
        dto.setName(conference.getName());

        return dto;
    }

    @Override
    public List<ConferenceDto> convertToListDto(List<Conference> conferences) {
        return conferences
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Conference convert(ConferenceDto conferenceDto) {
        Conference conference = new Conference();
        conference.setId(conferenceDto.getId());
        conference.setMaxSeats(conferenceDto.getMaxSeats());
        conference.setName(conferenceDto.getName());
        conference.setIsAvailable(conferenceDto.getIsAvailable());

        return conference;
    }

    @Override
    public List<Conference> convertToList(List<ConferenceDto> conferenceDtos) {
        return conferenceDtos
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
