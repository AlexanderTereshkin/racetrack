package com.company.racetrack.domain;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
*/

//@Entity
public class Track {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@NotNull(message = "You have to specify a name of this track.")
    private String nameOfTrack;

    private TrackStatus trackStatus;

    public Track() {
        trackStatus = TrackStatus.FREE;
    }

    public Track(int id, String nameOfTrack, TrackStatus trackStatus) {
        this.id = id;
        this.nameOfTrack = nameOfTrack;
        this.trackStatus = trackStatus;
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

    private enum TrackStatus {
        FREE, BUZY
    }
}
