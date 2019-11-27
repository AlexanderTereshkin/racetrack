package com.company.racetrack.repositories;

import com.company.racetrack.domain.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
