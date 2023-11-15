package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.Line;
import com.internship.coachbookingapi.repository.ILineRepository;
import com.internship.coachbookingapi.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LineServiceImpl implements ILineService {
    private final ILineRepository lineRepository;

    @Autowired
    public LineServiceImpl(ILineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @Override
    public List<Line> findAll() {
        return lineRepository.findAll();
    }

    @Override
    public Optional<Line> findById(UUID uuid) {
        return lineRepository.findById(uuid);
    }

    @Override
    public List<Line> findByStatusAndDepartureAndDestination(boolean status, Departure departure, Destination destination) {
        return lineRepository.findByStatusAndDepartureAndDestination(status, departure, destination);
    }

    @Override
    public List<Line> findByStatusAndDepartureAndDestinationAndDepartureDate(boolean status, Departure departure, Destination destination, Date departureDate) {
        return lineRepository.findByStatusAndDepartureAndDestinationAndDepartureDate(status, departure, destination, departureDate);
    }

    @Override
    public <S extends Line> S save(S entity) {
        return lineRepository.save(entity);
    }
}
