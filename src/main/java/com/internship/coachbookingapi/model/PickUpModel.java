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
public class PickUpModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String location;

//    @JsonIgnore
//    private PickUpTypeModel pickUpType;
//
//    @JsonIgnore
//    private DepartureModel departure;
//
//    @JsonIgnore
//    private List<TicketModel> tickets = new ArrayList<>();
}
