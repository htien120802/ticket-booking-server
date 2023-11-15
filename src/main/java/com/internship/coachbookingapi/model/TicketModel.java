package com.internship.coachbookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

//    private boolean is_oneway;

    private int number;

    private String note;

    private String street_number;

    private double cost;

    private boolean is_paid;

    private List<PickUpModel> pickUps = new ArrayList<>();

    private List<DropOffModel> dropOffs = new ArrayList<>();

    private List<PassengerModel> passengers = new ArrayList<>();

    private List<LineModel> lines = new ArrayList<>();

    private List<LineSeatModel> lineSeats = new ArrayList<>();
}
