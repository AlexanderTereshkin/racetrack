package com.company.racetrack.repositories;

import com.company.racetrack.domain.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackRepository extends CrudRepository<Track, Long> {
}
