package com.test.test_task.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
@Validated
public class RoomDto {
    private Long id;
    @Min(1)
    @NotNull
    private Long maxSeats;
}
