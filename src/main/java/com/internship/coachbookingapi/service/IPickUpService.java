package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.PickUp;
import com.internship.coachbookingapi.entity.PickUpType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPickUpService {
    List<PickUp> findAll();

    Optional<PickUp> findById(UUID uuid);

    <S extends PickUp> S save(S entity);

    List<PickUp> findByDepartureAndPickUpType(Departure departure, PickUpType pickUpType);
}
