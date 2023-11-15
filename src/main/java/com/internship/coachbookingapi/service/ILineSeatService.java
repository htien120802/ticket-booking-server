package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.LineSeat;
import com.internship.coachbookingapi.entity.LineSeatKey;

import java.util.List;
import java.util.Optional;

public interface ILineSeatService {
    List<LineSeat> findAll();

    Optional<LineSeat> findById(LineSeatKey lineSeatKey);

    <S extends LineSeat> S save(S entity);
}
