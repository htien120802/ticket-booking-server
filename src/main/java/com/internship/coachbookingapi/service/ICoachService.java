package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Coach;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICoachService {
    List<Coach> findAll();

    <S extends Coach> S save(S entity);

    Optional<Coach> findById(UUID uuid);
}
