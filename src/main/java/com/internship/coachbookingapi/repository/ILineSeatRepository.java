package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.LineSeat;
import com.internship.coachbookingapi.entity.LineSeatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILineSeatRepository extends JpaRepository<LineSeat, LineSeatKey> {
}
