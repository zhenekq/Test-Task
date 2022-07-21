package com.test.test_task.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Participants included in Conference
 *
 * @author Eugene
 * @version 1.0
 * @see Conference with participants
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
