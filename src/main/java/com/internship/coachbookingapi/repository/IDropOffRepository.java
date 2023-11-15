package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.DropOff;
import com.internship.coachbookingapi.entity.DropOffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDropOffRepository extends JpaRepository<DropOff, UUID> {
    List<DropOff> findByDestinationAndDropOffType(Destination destination, DropOffType dropOffType);
}
