package com.test.test_task.service;

import com.test.test_task.dto.ParticipantDto;
import com.test.test_task.entity.Participant;

/**
 * Service layer for validation and business logic for ParticipantRepository
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.repositoty.ParticipantRepository
 */

public interface ParticipantService {

    /**
     * @param participant body of Participant
     * @param conferenceId which conference need to be included
     * @return added participant
     * @see com.test.test_task.service.impl.ParticipantServiceImpl
     */
    ParticipantDto addToConference(Participant participant, Long conferenceId);

    /**
     * @param conferenceId where need to delete Participant
     * @param participantId who needed to be deleted
     * @return added participant
     * @see com.test.test_task.service.impl.ParticipantServiceImpl
     */
    ParticipantDto deleteById(Long conferenceId, Long participantId);

}
