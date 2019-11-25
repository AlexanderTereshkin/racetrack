package racetrack.entities;

public class Track {
    private int id;
    private String nameOfTrack;
    private TrackStatus trackStatus;

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
