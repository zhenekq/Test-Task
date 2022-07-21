package com.test.test_task.repositoty;

import com.test.test_task.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByConferenceId(Long conferenceId);
}
