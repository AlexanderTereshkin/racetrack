package com.company.racetrack.repositories;

import com.company.racetrack.domain.Race;
import com.company.racetrack.domain.RaceRacerCarLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RaceRacerCarLinkRepository extends CrudRepository<RaceRacerCarLink, Long> {

    /*@Query(value = "SELECT * FROM race_racer_car WHERE MIN(result_time) GROUP BY race_id", nativeQuery = true)  -   find all winners for each race  */


    /*@Query("SELECT winner FROM RaceRacerCarLink winner WHERE MIN(winner.resultTime)")
    RaceRacerCarLink findWinnerByRace(Race race);*/

}
