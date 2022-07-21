package com.test.test_task.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.ALL
    })
    @JoinTable(
            name = "conference_participant",
            joinColumns = { @JoinColumn(name = "participant_id") },
            inverseJoinColumns = { @JoinColumn(name = "conference_id") }
    )
    private List<Conference> conference;
}
