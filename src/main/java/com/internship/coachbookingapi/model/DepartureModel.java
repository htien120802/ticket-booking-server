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
public class DepartureModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private String slug;

    private List<PickUpModel> pickUps = new ArrayList<>();

//    @JsonIgnore
//    private List<LineModel> lines = new ArrayList<>();

    private List<DestinationModel> destinations = new ArrayList<>();
}
