package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.DropOff;
import com.internship.coachbookingapi.entity.DropOffType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDropOffService {
    List<DropOff> findAll();

    List<DropOff> findByDestinationAndDropOffType(Destination destination, DropOffType dropOffType);

    Optional<DropOff> findById(UUID uuid);

    <S extends DropOff> S save(S entity);
}
