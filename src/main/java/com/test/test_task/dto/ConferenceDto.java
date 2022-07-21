package com.test.test_task.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

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
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
