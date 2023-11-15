package com.internship.coachbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class LineSeat {
    @EmbeddedId
    private LineSeatKey lineSeatKey;

    @Column(columnDefinition = "tinyInt(1) default 1")
    private boolean is_available;

    @ManyToOne
    @JoinColumn(name = "line_id", insertable = false, updatable = false)
    private Line line;

    @ManyToOne
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
