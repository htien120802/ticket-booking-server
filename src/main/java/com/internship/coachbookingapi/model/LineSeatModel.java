package com.internship.coachbookingapi.model;

import com.internship.coachbookingapi.entity.LineSeatKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineSeatModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LineSeatKey lineSeatKey;

    private boolean is_available;

//    @JsonIgnore
//    private LineModel line;

    private SeatModel seat;

//    @JsonIgnore
//    private TicketModel ticket;
}
