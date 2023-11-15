package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, UUID> {
    Optional<Destination> findBySlug(String slug);
}
