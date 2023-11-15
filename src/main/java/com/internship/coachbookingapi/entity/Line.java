package com.internship.coachbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Line {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    private Time departureTime;

    private Date departureDate;

    private double price;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Departure departure;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @OneToMany(mappedBy = "line")
    private List<LineSeat> lineSeats = new ArrayList<>();

    @ManyToMany(mappedBy = "lines", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
