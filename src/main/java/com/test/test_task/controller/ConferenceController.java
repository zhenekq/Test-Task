package com.test.test_task.controller;

import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.service.ConferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller that process Conference Logic
 * @see ConferenceService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/conference")
@Validated
public class ConferenceController {

    private final ConferenceService conferenceService;
    private final ModelMapper modelMapper;

    public ConferenceController(ConferenceService conferenceService, ModelMapper modelMapper) {
        this.conferenceService = conferenceService;
        this.modelMapper = modelMapper;
    }

    /**
     * POST request to create new conference
     *
     * @param conferenceDto body of conference
     * @return Created Conference
     */
    @PostMapping("/room/{roomId}")
    public ConferenceDto create(@Valid @RequestBody ConferenceDto conferenceDto,
                                @PathVariable Long roomId, Errors errors){
        Conference conference = modelMapper.map(conferenceDto, Conference.class);

        Conference newConference = conferenceService.create(conference, roomId);
        return modelMapper.map(newConference, ConferenceDto.class);
    }

    /**
     * DELETE request to Cancel current Conference
     *
     * @param conferenceId needed conference
     * @return Deleted Conference
     */
    @DeleteMapping("{conferenceId}/cancel")
    public ConferenceDto cancel(@PathVariable Long conferenceId){
        Conference conference = conferenceService.cancelById(conferenceId);
        return modelMapper.map(conference, ConferenceDto.class);
    }

}
