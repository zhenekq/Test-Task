package com.test.test_task.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Conference(Long id) {
        this.id = id;
    }
}
