package com.internship.coachbookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PassengerModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private int year_of_birth;

    private String email;

    private String phone;

    private boolean is_booking;

//    @JsonIgnore
//    private TicketModel ticket;
}
