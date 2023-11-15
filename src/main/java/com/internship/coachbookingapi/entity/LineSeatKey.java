package com.internship.coachbookingapi.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LineSeatKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID line_id;

    private UUID seat_id;
}
