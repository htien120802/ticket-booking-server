package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.PickUpType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPickUpTypeService {
    List<PickUpType> findAll();

    <S extends PickUpType> S save(S entity);

    Optional<PickUpType> findById(UUID uuid);
}
