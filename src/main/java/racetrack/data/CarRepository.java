package racetrack.data;

import org.springframework.data.repository.CrudRepository;
import racetrack.entities.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
