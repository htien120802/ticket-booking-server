package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Destination;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDestinationService {
    List<Destination> findAll();

    Optional<Destination> findById(UUID uuid);

    Optional<Destination> findBySlug(String slug);

    <S extends Destination> S save(S entity);
}
