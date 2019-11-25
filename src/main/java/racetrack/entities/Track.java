package racetrack.entities;

public class Track {
    private int id;
    private String nameOfTrack;
    private String status;

    public Track(int id, String nameOfTrack, String status) {
        this.id = id;
        this.nameOfTrack = nameOfTrack;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
