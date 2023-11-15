package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.repository.IDestinationRepository;
import com.internship.coachbookingapi.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationServiceImpl implements IDestinationService {
    private final IDestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(IDestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Optional<Destination> findById(UUID uuid) {
        return destinationRepository.findById(uuid);
    }

    @Override
    public Optional<Destination> findBySlug(String slug) {
        return destinationRepository.findBySlug(slug);
    }

    @Override
    public <S extends Destination> S save(S entity) {
        return destinationRepository.save(entity);
    }
}
