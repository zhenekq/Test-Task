package com.test.test_task.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

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
@Validated
public class ParticipantDto {
    private Long id;
    @NotBlank
    private String username;
}
