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
public class PickUp {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(length = 100)
    private String location;

    @ManyToOne
    @JoinColumn(name = "pick_up_type_id")
    private PickUpType pickUpType;

    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Departure departure;

    @ManyToMany(mappedBy = "pickUps", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
