package com.company.racetrack.repositories;

import com.company.racetrack.domain.Race;
import org.springframework.data.repository.CrudRepository;

public interface RaceRepository extends CrudRepository<Race, Long>{
}
