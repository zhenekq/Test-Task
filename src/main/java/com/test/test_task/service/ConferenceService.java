package com.test.test_task.service;

import com.test.test_task.entity.Conference;


/**
 * Service layer for validation and business logic for ConferenceRepository
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.repositoty.ConferenceRepository
 */

public interface ConferenceService {
    /**
     * @param conference body of created conference
     * @return new conference
     * @see com.test.test_task.service.impl.ConferenceServiceImpl
     */
    Conference create(Conference conference, Long roomId);


    /**
     * @param conferenceId of current conference
     * @return Canceled conference
     * @see com.test.test_task.service.impl.ConferenceServiceImpl
     */
    Conference cancelById(Long conferenceId);
}
