package com.company.racetrack.repositories;

import com.company.racetrack.domain.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll(Pageable pageable);

}
