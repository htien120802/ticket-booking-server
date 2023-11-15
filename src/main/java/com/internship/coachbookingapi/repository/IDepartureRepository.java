package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDepartureRepository extends JpaRepository<Departure, UUID> {
    Optional<Departure> findBySlug(String slug);
}
