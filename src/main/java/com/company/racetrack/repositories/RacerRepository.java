package com.company.racetrack.repositories;

import com.company.racetrack.domain.Racer;
import com.company.racetrack.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RacerRepository extends CrudRepository<Racer, Long> {

    List<Racer> findRacerByTeam(Team team);

}
