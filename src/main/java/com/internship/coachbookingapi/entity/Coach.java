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
public class Coach {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @OneToMany(mappedBy = "coach")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "coach")
    private List<Line> lines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "coach_type_id")
    private CoachType coachType;
}
