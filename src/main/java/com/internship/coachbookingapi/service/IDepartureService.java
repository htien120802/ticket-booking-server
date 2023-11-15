package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Departure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDepartureService {
    List<Departure> findAll();

    Optional<Departure> findById(UUID uuid);

    Optional<Departure> findBySlug(String slug);

    <S extends Departure> S save(S entity);
}
