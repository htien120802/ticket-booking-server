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
public class Destination {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 100, unique = true)
    private String slug;

    @OneToMany(mappedBy = "destination")
    private List<DropOff> dropOffs = new ArrayList<>();

    @OneToMany(mappedBy = "destination")
    private List<Line> lines = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "departure_destination",
            joinColumns = @JoinColumn(name = "departureId"),
            inverseJoinColumns = @JoinColumn(name = "destinationId"))
    private List<Departure> departures = new ArrayList<>();
}
