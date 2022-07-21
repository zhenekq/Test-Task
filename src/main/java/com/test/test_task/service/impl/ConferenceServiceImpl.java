package com.test.test_task.service.impl;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.repositoty.ConferenceRepository;
import com.test.test_task.service.ConferenceService;
import com.test.test_task.util.ExceptionStorage;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

/**
 * Implementation of ConferenceService
 *
 * @author Eugene
 * @version 1.0
 * @see ConferenceService
 */

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final EntityConverter<Conference, ConferenceDto> conferenceConverter;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, EntityConverter<Conference, ConferenceDto> conferenceConverter) {
        this.conferenceRepository = conferenceRepository;
        this.conferenceConverter = conferenceConverter;
    }

    @Override
    public ConferenceDto create(Conference conference) {
        conference.setStartDate(LocalDateTime.now());
        conference.setEndDate(LocalDateTime.now());
        Conference newConference = conferenceRepository.save(conference);
        return conferenceConverter.convertToDto(newConference);
    }


    @Override
    public ConferenceDto cancelById(Long conferenceId) {
        Conference conference = conferenceRepository
                .findById(conferenceId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionStorage.conferenceNotExists(conferenceId)));
        conferenceRepository.deleteById(conferenceId);
        return conferenceConverter.convertToDto(conference);
    }

}
