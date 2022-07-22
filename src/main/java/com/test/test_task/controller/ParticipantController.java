package com.test.test_task.controller;

import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Participant;
import com.test.test_task.service.ConferenceService;
import com.test.test_task.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller that process Participant Logic
 * @see ConferenceService
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/participants")
@Validated
public class ParticipantController {

    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    public ParticipantController(ParticipantService participantService, ModelMapper modelMapper) {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }

    /**
     * POST request to create new participant
     *
     * @param participantDto body of new participant
     * @return Created Participant
     */
    @PostMapping
    public ParticipantDto create(@Valid  @RequestBody ParticipantDto participantDto, Errors errors){
        Participant participant = modelMapper.map(participantDto, Participant.class);
        Participant newParticipant = participantService.create(participant);
        return modelMapper.map(newParticipant, ParticipantDto.class);
    }

    @PostMapping("{participantId}/attach/{conferenceId}")
    public ParticipantDto attach(@PathVariable Long participantId,
                                 @PathVariable Long conferenceId){

        Participant participant = participantService.addToConference(participantId, conferenceId);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    /**
     * DELETE request to delete participant from conference
     *
     * @param conferenceId exists conference
     * @param participantId participant needed to be deleted
     * @return Delete Participant
     */
    @DeleteMapping("{participantId}/leave/{conferenceId}") ParticipantDto delete(@PathVariable Long conferenceId,
                                                               @PathVariable Long participantId){
        Participant participant = participantService.deleteById(conferenceId, participantId);
        return modelMapper.map(participant, ParticipantDto.class);
    }

}
