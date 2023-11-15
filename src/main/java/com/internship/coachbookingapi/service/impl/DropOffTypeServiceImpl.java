package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.DropOffType;
import com.internship.coachbookingapi.repository.IDropOffTypeRepository;
import com.internship.coachbookingapi.service.IDropOffTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DropOffTypeServiceImpl implements IDropOffTypeService {
    private final IDropOffTypeRepository dropOffTypeRepository;

    @Autowired
    public DropOffTypeServiceImpl(IDropOffTypeRepository dropOffTypeRepository) {
        this.dropOffTypeRepository = dropOffTypeRepository;
    }

    @Override
    public List<DropOffType> findAll() {
        return dropOffTypeRepository.findAll();
    }

    @Override
    public <S extends DropOffType> S save(S entity) {
        return dropOffTypeRepository.save(entity);
    }

    @Override
    public Optional<DropOffType> findById(UUID uuid) {
        return dropOffTypeRepository.findById(uuid);
    }
}
