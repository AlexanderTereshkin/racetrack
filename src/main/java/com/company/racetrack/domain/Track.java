package com.company.racetrack.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "track", cascade = CascadeType.REMOVE)
    private Set<Race> races = new HashSet<>();

    @NotNull(message = "You have to specify a name of this track.")
    private String nameOfTrack;

    private TrackStatus trackStatus;

    public Track() {
        trackStatus = TrackStatus.FREE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfTrack() {
        return nameOfTrack;
    }

    public void setNameOfTrack(String nameOfTrack) {
        this.nameOfTrack = nameOfTrack;
    }

    public TrackStatus getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(TrackStatus trackStatus) {
        this.trackStatus = trackStatus;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    private enum TrackStatus {
        FREE, BUZY
    }
}
