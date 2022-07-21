package com.test.test_task.dto;

import lombok.*;

/**
 * Data transfer object for entity Participant
 *
 * @author Eugene
 * @version 1.0
 * @see com.test.test_task.entity.Participant
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ParticipantDto {
    private Long id;
    private String username;
}
