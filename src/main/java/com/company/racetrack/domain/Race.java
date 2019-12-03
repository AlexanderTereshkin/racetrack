package com.company.racetrack.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name="races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "track_id", nullable = false)
    @NotNull(message = "You must to choose a track, where this race will start.")
    private Track track;

    //private RaceRacerCarLink winner;

    /*@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    @Size(min = 4, message = "In the race have to be no less than 4 riders.")
    @Size(max = 6, message = "In the race have to be no more than 6 riders.")
    private Set<Racer> racers = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    @Size(min = 4, message = "In the race have to be no less than 4 cars.")
    @Size(max = 6, message = "In the race have to be no more than 6 cars.")
    private Set<Car> cars = new HashSet<>();*/

    @OneToMany(mappedBy = "race", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<RaceRacerCarLink> raceRacerCarLink;

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

    /*public RaceRacerCarLink getWinner() {
        return winner;
    }

    public void setWinner(RaceRacerCarLink winner) {
        this.winner = winner;
    }*/

    public Set<RaceRacerCarLink> getRaceRacerCarLink() {
        return raceRacerCarLink;
    }

    public void setRaceRacerCarLink(Set<RaceRacerCarLink> raceRacerCarLink) {
        this.raceRacerCarLink = raceRacerCarLink;
    }
}
