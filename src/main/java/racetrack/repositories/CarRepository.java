package racetrack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import racetrack.domain.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
