package com.internship.coachbookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachTypeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

//    @JsonIgnore
//    private List<CoachModel> coaches = new ArrayList<>();
}
