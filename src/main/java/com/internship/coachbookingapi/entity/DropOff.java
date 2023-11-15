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
public class DropOff {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(length = 100)
    private String location;

    @ManyToOne
    @JoinColumn(name = "drop_off_type_id")
    private DropOffType dropOffType;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToMany(mappedBy = "dropOffs", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
