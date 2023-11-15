package com.internship.coachbookingapi.service;

import com.internship.coachbookingapi.entity.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITicketService {
    List<Ticket> findAll();

    Optional<Ticket> findById(UUID uuid);

    <S extends Ticket> S save(S entity);
}
