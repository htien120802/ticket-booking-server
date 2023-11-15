package com.internship.coachbookingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Passenger {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(length = 100)
    private String name;

    private int year_of_birth;

    @Column(length = 50)
    private String email;

    @Column(length = 15)
    private String phone;

    private boolean is_booking;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
