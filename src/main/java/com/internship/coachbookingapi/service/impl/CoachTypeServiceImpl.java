package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.repository.ICoachTypeRepository;
import com.internship.coachbookingapi.service.ICoachTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoachTypeServiceImpl implements ICoachTypeService {
    private final ICoachTypeRepository coachTypeRepository;

    @Autowired
    public CoachTypeServiceImpl(ICoachTypeRepository coachTypeRepository) {
        this.coachTypeRepository = coachTypeRepository;
    }

    @Override
    public List<CoachType> findAll() {
        return coachTypeRepository.findAll();
    }

    @Override
    public Optional<CoachType> findById(UUID uuid) {
        return coachTypeRepository.findById(uuid);
    }

    @Override
    public <S extends CoachType> S save(S entity) {
        return coachTypeRepository.save(entity);
    }
}
