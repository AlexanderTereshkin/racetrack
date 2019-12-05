package com.company.racetrack.repositories;

import com.company.racetrack.domain.Car;
import com.company.racetrack.domain.Race;
import com.company.racetrack.domain.RaceRacerCarLink;
import com.company.racetrack.domain.Racer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaceRacerCarLinkRepository extends CrudRepository<RaceRacerCarLink, Long> {

    @Query(value = "SELECT racer_id FROM race_racer_car WHERE race_id = :current_race_id AND racer_id = :current_racer_id", nativeQuery = true)
    Long findRacerByRace(@Param("current_race_id") Race race, @Param("current_racer_id") Racer racer);

    @Query(value = "SELECT car_id FROM race_racer_car WHERE race_id = :current_race_id AND car_id = :current_car_id", nativeQuery = true)
    Long findCarByRace(@Param("current_race_id") Race race, @Param("current_car_id") Car car);

    List<RaceRacerCarLink> findByRacer(Racer racer);

    @Query(value = "SELECT * FROM race_racer_car WHERE racer_id = :current_racer_id GROUP BY race_id", nativeQuery = true)
    List<RaceRacerCarLink> findRacesByRacer(@Param("current_racer_id") Racer racer);

    @Query(value = "SELECT * FROM race_racer_car WHERE racer_id = :current_racer_id AND car_id = :current_car_id GROUP BY race_id", nativeQuery = true)
    List<RaceRacerCarLink> findRacesByRacerByCar(@Param("current_racer_id") Racer racer, @Param("current_car_id") Car car);
}
