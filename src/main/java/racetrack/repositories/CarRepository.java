package racetrack.repositories;

import org.springframework.data.repository.CrudRepository;
import racetrack.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
