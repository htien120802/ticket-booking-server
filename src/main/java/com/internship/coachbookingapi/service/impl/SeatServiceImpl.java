package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Seat;
import com.internship.coachbookingapi.repository.ISeatRepository;
import com.internship.coachbookingapi.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatServiceImpl implements ISeatService {
    private final ISeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Optional<Seat> findById(UUID uuid) {
        return seatRepository.findById(uuid);
    }

    @Override
    public <S extends Seat> S save(S entity) {
        return seatRepository.save(entity);
    }
}
