package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.CoachType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICoachTypeRepository extends JpaRepository<CoachType, UUID> {
}
