package com.internship.coachbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Ticket {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

//    private boolean is_oneway;

    private int number;

    private String note;

    @Column(length = 100)
    private String street_number;

    private double cost;

    private boolean is_paid;

    @ManyToMany
    @JoinTable(name = "ticket_pick_up",
            joinColumns = @JoinColumn(name = "pickUpId"),
            inverseJoinColumns = @JoinColumn(name = "ticketId"))
    private List<PickUp> pickUps = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ticket_drop_off",
            joinColumns = @JoinColumn(name = "dropOffId"),
            inverseJoinColumns = @JoinColumn(name = "ticketId"))
    private List<DropOff> dropOffs = new ArrayList<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ticket_line",
            joinColumns = @JoinColumn(name = "lineId"),
            inverseJoinColumns = @JoinColumn(name = "ticketId"))
    private List<Line> lines = new ArrayList<>();

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<LineSeat> lineSeats = new ArrayList<>();
}
