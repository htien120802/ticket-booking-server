package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.DropOff;
import com.internship.coachbookingapi.entity.DropOffType;
import com.internship.coachbookingapi.repository.IDropOffRepository;
import com.internship.coachbookingapi.service.IDropOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DropOffServiceImpl implements IDropOffService {
    private final IDropOffRepository dropOffRepository;

    @Autowired
    public DropOffServiceImpl(IDropOffRepository dropOffRepository) {
        this.dropOffRepository = dropOffRepository;
    }

    @Override
    public List<DropOff> findAll() {
        return dropOffRepository.findAll();
    }

    @Override
    public List<DropOff> findByDestinationAndDropOffType(Destination destination, DropOffType dropOffType) {
        return dropOffRepository.findByDestinationAndDropOffType(destination, dropOffType);
    }

    @Override
    public Optional<DropOff> findById(UUID uuid) {
        return dropOffRepository.findById(uuid);
    }

    @Override
    public <S extends DropOff> S save(S entity) {
        return dropOffRepository.save(entity);
    }
}
