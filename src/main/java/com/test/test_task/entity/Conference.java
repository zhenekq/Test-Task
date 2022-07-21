package com.test.test_task.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Conference with participants
 *
 * @author Eugene
 * @version 1.0
 * @see Participant connected to conference
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "max_seats")
    private Long maxSeats;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    public Conference(Long id) {
        this.id = id;
    }
}
