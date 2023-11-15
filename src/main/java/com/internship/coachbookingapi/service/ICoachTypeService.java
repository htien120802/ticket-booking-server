package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.CoachType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICoachTypeService {
    List<CoachType> findAll();

    Optional<CoachType> findById(UUID uuid);

    <S extends CoachType> S save(S entity);
}
