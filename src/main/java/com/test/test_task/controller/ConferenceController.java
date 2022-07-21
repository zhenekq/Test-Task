package com.test.test_task.controller;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ConferenceDto;
import com.test.test_task.entity.Conference;
import com.test.test_task.service.ConferenceService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that process Conference Logic
 * @see ConferenceService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;
    private final EntityConverter<Conference, ConferenceDto> conferenceConverter;

    public ConferenceController(ConferenceService conferenceService, EntityConverter<Conference, ConferenceDto> conferenceConverter) {
        this.conferenceService = conferenceService;
        this.conferenceConverter = conferenceConverter;
    }

    /**
     * POST request to create new conference
     *
     * @param conferenceDto body of conference
     * @return Created Conference
     */
    @PostMapping("/room/{roomId}")
    public ConferenceDto create(@RequestBody ConferenceDto conferenceDto,
                                @PathVariable Long roomId){
        Conference conference = conferenceConverter.convert(conferenceDto);

        return conferenceService.create(conference, roomId);
    }

    /**
     * DELETE request to Cancel current Conference
     *
     * @param conferenceId needed conference
     * @return Deleted Conference
     */
    @DeleteMapping("{conferenceId}/cancel")
    public ConferenceDto cancel(@PathVariable Long conferenceId){
        return conferenceService.cancelById(conferenceId);
    }

}
