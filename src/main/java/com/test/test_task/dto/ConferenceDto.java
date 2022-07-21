package com.test.test_task.dto;

import lombok.*;

/**
 * Data transfer object for entity Conference
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.entity.Conference
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ConferenceDto {
    private Long id;
    private Long maxSeats;
    private String name;
    private Boolean isAvailable;
}
