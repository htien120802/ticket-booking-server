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
public class CoachDto implements Serializable {
    @Serial
    private UUID id;

    private List<SeatModel> seats = new ArrayList<>();

    private List<LineModel> lines = new ArrayList<>();

//    @JsonIgnore
//    private CoachTypeModel coachType;
}
