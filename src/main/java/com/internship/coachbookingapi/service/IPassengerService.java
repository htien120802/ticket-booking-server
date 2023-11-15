package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Passenger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPassengerService {
    List<Passenger> findAll();

    Optional<Passenger> findById(UUID uuid);

    <S extends Passenger> S save(S entity);
}
