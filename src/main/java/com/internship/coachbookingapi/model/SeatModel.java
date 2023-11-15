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
public class SeatModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String position;

    private int decker;

    private boolean is_blank;

//    @JsonIgnore
//    private CoachModel coach;
//
//    @JsonIgnore
//    private List<LineSeatModel> lineSeats = new ArrayList<>();
}
