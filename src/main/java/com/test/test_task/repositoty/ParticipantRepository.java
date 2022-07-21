package com.test.test_task.repositoty;

import com.test.test_task.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for working with Participants
 *
 * @author Eugene
 * @version 1.0
 * @see Participant
 */

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    /**
     * @param conferenceId of out current conference
     * @return Amount of participants that included in conference
     */
    Long countParticipantsByConferenceId(Long conferenceId);
}
