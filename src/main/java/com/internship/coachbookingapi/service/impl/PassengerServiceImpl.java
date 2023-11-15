package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Passenger;
import com.internship.coachbookingapi.repository.IPassengerRepository;
import com.internship.coachbookingapi.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PassengerServiceImpl implements IPassengerService {
    private final IPassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(IPassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> findById(UUID uuid) {
        return passengerRepository.findById(uuid);
    }

    @Override
    public <S extends Passenger> S save(S entity) {
        return passengerRepository.save(entity);
    }
}
