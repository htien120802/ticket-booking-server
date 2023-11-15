package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ILineRepository extends JpaRepository<Line, UUID> {
    List<Line> findByStatusAndDepartureAndDestination(boolean status, Departure departure, Destination destination);

    List<Line> findByStatusAndDepartureAndDestinationAndDepartureDate(boolean status, Departure departure, Destination destination, Date departureDate);
}
