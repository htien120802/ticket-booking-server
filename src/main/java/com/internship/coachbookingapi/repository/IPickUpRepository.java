package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.PickUp;
import com.internship.coachbookingapi.entity.PickUpType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPickUpRepository extends JpaRepository<PickUp, UUID> {
    List<PickUp> findByDepartureAndPickUpType(Departure departure, PickUpType pickUpType);
}
