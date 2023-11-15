package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Seat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISeatService {
    List<Seat> findAll();

    Optional<Seat> findById(UUID uuid);

    <S extends Seat> S save(S entity);
}
