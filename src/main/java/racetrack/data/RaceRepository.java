package racetrack.data;

import org.springframework.data.repository.CrudRepository;
import racetrack.entities.Race;

public interface RaceRepository extends CrudRepository<Race, Long>{
}
