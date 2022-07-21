package com.test.test_task.controller;

import com.test.test_task.converter.EntityConverter;
import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Participant;
import com.test.test_task.service.ConferenceService;
import com.test.test_task.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that process Participant Logic
 * @see ConferenceService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/conference/{conferenceId}/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    private final EntityConverter<Participant, ParticipantDto> participantConverter;

    public ParticipantController(ParticipantService participantService, EntityConverter<Participant, ParticipantDto> participantConverter) {
        this.participantService = participantService;
        this.participantConverter = participantConverter;
    }

    /**
     * POST request to create new participant
     *
     * @param conferenceId exists conference
     * @param participantDto body of new participant
     * @return Created Participant
     */
    @PostMapping
    public ParticipantDto create(@PathVariable Long conferenceId,
                                 @RequestBody ParticipantDto participantDto){
        Participant participant = participantConverter.convert(participantDto);
        return participantService.addToConference(participant, conferenceId);
    }

    /**
     * DELETE request to delete participant from conference
     *
     * @param conferenceId exists conference
     * @param participantId participant needed to be deleted
     * @return Delete Participant
     */
    @DeleteMapping("{participantId}") ParticipantDto delete(@PathVariable Long conferenceId,
                                                               @PathVariable Long participantId){
        return participantService.deleteById(conferenceId, participantId);
    }

}