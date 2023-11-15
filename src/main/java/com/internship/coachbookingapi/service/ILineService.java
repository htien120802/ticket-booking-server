package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.Line;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILineService {
    List<Line> findAll();

    Optional<Line> findById(UUID uuid);

    List<Line> findByStatusAndDepartureAndDestination(boolean status, Departure departure, Destination destination);

    List<Line> findByStatusAndDepartureAndDestinationAndDepartureDate(boolean status, Departure departure, Destination destination, Date departureDate);

    <S extends Line> S save(S entity);
}
