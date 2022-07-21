package com.test.test_task.converter.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter for Conference
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see EntityConverter
 */

@Component
public class ConferenceConverter implements EntityConverter<Conference, ConferenceDto> {
    @Override
    public ConferenceDto convertToDto(Conference conference) {
        ConferenceDto dto = new ConferenceDto();
        dto.setId(conference.getId());
        dto.setName(conference.getName());
        dto.setEndDate(conference.getEndDate());
        dto.setStartDate(conference.getStartDate());
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
        conference.setName(conferenceDto.getName());
        conference.setEndDate(conferenceDto.getEndDate());
        conference.setStartDate(conferenceDto.getStartDate());
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
