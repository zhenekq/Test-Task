package com.test.test_task.dto;

import lombok.*;

/**
 * Data transfer object for entity Room
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.entity.Room
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomDto {
    private Long id;
    private Long maxSeats;
}
