package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.PickUp;
import com.internship.coachbookingapi.entity.PickUpType;
import com.internship.coachbookingapi.repository.IPickUpRepository;
import com.internship.coachbookingapi.service.IPickUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PickUpServiceImpl implements IPickUpService {
    private final IPickUpRepository pickUpRepository;

    @Autowired
    public PickUpServiceImpl(IPickUpRepository pickUpRepository) {
        this.pickUpRepository = pickUpRepository;
    }

    @Override
    public List<PickUp> findAll() {
        return pickUpRepository.findAll();
    }

    @Override
    public Optional<PickUp> findById(UUID uuid) {
        return pickUpRepository.findById(uuid);
    }

    @Override
    public <S extends PickUp> S save(S entity) {
        return pickUpRepository.save(entity);
    }

    @Override
    public List<PickUp> findByDepartureAndPickUpType(Departure departure, PickUpType pickUpType) {
        return pickUpRepository.findByDepartureAndPickUpType(departure, pickUpType);
    }
}
