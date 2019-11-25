package racetrack.data;

import org.springframework.data.repository.CrudRepository;
import racetrack.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
