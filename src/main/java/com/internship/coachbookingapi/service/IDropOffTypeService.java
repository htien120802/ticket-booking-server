package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.DropOffType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDropOffTypeService {
    List<DropOffType> findAll();

    <S extends DropOffType> S save(S entity);

    Optional<DropOffType> findById(UUID uuid);
}
