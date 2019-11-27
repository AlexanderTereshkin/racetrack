package com.company.racetrack.repositories;

import com.company.racetrack.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
