package com.company.racetrack.repositories;

import com.company.racetrack.domain.Racer;
import org.springframework.data.repository.CrudRepository;

public interface RacerRepository extends CrudRepository<Racer, Long> {
}
