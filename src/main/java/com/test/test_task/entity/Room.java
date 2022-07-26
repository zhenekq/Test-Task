package com.test.test_task.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Conference room
 *
 * @author Eugene
 * @version 1.0
 * @see Conference
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long maxSeats;

    @OneToOne(mappedBy = "room")
    private Conference conference;
}
