package com.test.test_task.repositoty;

import com.test.test_task.entity.Conference;
import com.test.test_task.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<Conference> findAllByRoomId(Long roomId);

    Conference findByName(String name);
}
