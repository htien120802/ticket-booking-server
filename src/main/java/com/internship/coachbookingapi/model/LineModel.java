package com.internship.coachbookingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private Time departureTime;

    private Date departureDate;

    private double price;

    private boolean status;

    @JsonIgnore
    private Departure departure;

    @JsonIgnore
    private Destination destination;

    @JsonIgnore
    private CoachDto coach;

    private List<LineSeatModel> lineSeats = new ArrayList<>();

//    @JsonIgnore
//    private List<TicketModel> tickets = new ArrayList<>();
}
