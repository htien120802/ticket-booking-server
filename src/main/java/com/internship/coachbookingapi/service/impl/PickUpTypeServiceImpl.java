package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.PickUpType;
import com.internship.coachbookingapi.repository.IPickUpTypeRepository;
import com.internship.coachbookingapi.service.IPickUpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PickUpTypeServiceImpl implements IPickUpTypeService {
    private final IPickUpTypeRepository pickUpTypeRepository;

    @Autowired
    public PickUpTypeServiceImpl(IPickUpTypeRepository pickUpTypeRepository) {
        this.pickUpTypeRepository = pickUpTypeRepository;
    }

    @Override
    public List<PickUpType> findAll() {
        return pickUpTypeRepository.findAll();
    }

    @Override
    public <S extends PickUpType> S save(S entity) {
        return pickUpTypeRepository.save(entity);
    }

    @Override
    public Optional<PickUpType> findById(UUID uuid) {
        return pickUpTypeRepository.findById(uuid);
    }
}
