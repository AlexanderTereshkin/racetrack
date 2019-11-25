package racetrack.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Status status;

    @NotNull(message = "You must to choose a track, where this race will start.")
    private Track track;

    @OneToMany(targetEntity = Racer.class)
    @Size(min = 5, max = 5, message = "In the race must to participate 5 racers.")
    private List<Racer> racerList;

    private enum Status {
        CREATED, ONGOING, FINISHED
    }

    public Race() {
        status = Status.CREATED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<Racer> getRacerList() {
        return racerList;
    }

    public void setRacerList(List<Racer> racerList) {
        this.racerList = racerList;
    }
}
