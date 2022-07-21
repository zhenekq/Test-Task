package com.test.test_task.repositoty;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for working with Conference
 *
 * @author Eugene
 * @version 1.0
 * @see Conference
 */

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    Conference findByRoomId(Long roomId);

}
