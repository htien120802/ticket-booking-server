package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.LineSeat;
import com.internship.coachbookingapi.entity.LineSeatKey;
import com.internship.coachbookingapi.repository.ILineSeatRepository;
import com.internship.coachbookingapi.service.ILineSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineSeatServiceImpl implements ILineSeatService {
    private final ILineSeatRepository lineSeatRepository;

    @Autowired
    public LineSeatServiceImpl(ILineSeatRepository lineSeatRepository) {
        this.lineSeatRepository = lineSeatRepository;
    }

    @Override
    public List<LineSeat> findAll() {
        return lineSeatRepository.findAll();
    }

    @Override
    public Optional<LineSeat> findById(LineSeatKey lineSeatKey) {
        return lineSeatRepository.findById(lineSeatKey);
    }

    @Override
    public <S extends LineSeat> S save(S entity) {
        return lineSeatRepository.save(entity);
    }
}
