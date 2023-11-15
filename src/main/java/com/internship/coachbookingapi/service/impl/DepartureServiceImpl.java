package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.repository.IDepartureRepository;
import com.internship.coachbookingapi.service.IDepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartureServiceImpl implements IDepartureService {
    private final IDepartureRepository departureRepository;

    @Autowired
    public DepartureServiceImpl(IDepartureRepository departureRepository) {
        this.departureRepository = departureRepository;
    }

    @Override
    public List<Departure> findAll() {
        return departureRepository.findAll();
    }

    @Override
    public Optional<Departure> findById(UUID uuid) {
        return departureRepository.findById(uuid);
    }

    @Override
    public Optional<Departure> findBySlug(String slug) {
        return departureRepository.findBySlug(slug);
    }

    @Override
    public <S extends Departure> S save(S entity) {
        return departureRepository.save(entity);
    }
}
