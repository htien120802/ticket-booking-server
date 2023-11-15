package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Coach;
import com.internship.coachbookingapi.repository.ICoachRepository;
import com.internship.coachbookingapi.service.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoachServiceImpl implements ICoachService {
    private final ICoachRepository coachRepository;

    @Autowired
    public CoachServiceImpl(ICoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public <S extends Coach> S save(S entity) {
        return coachRepository.save(entity);
    }

    @Override
    public Optional<Coach> findById(UUID uuid) {
        return coachRepository.findById(uuid);
    }
}
