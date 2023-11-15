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
public class Departure {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 100, unique = true)
    private String slug;

    @OneToMany(mappedBy = "departure")
    private List<PickUp> pickUps = new ArrayList<>();

    @OneToMany(mappedBy = "departure")
    private List<Line> lines = new ArrayList<>();

    @ManyToMany(mappedBy = "departures", cascade = CascadeType.ALL)
    private List<Destination> destinations = new ArrayList<>();
}
