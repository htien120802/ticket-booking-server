package com.internship.coachbookingapi.service.impl;

import com.internship.coachbookingapi.entity.Ticket;
import com.internship.coachbookingapi.repository.ITicketRepository;
import com.internship.coachbookingapi.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {
    private final ITicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findById(UUID uuid) {
        return ticketRepository.findById(uuid);
    }

    @Override
    public <S extends Ticket> S save(S entity) {
        return ticketRepository.save(entity);
    }
}
