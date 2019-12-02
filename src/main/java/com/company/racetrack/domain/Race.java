package com.company.racetrack.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "track_id", nullable = false)
    @NotNull(message = "You must to choose a track, where this race will start.")
    private Track track;

   /* @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
*//*    @JoinTable(name = "races_racers",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "racer_id"))*//*
    @Size(min = 4, message = "In the race have to be no less than 4 riders.")
    @Size(max = 6, message = "In the race have to be no more than 6 riders.")
    private Set<Racer> racers;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Size(min = 4, message = "In the race have to be no less than 4 cars.")
    @Size(max = 6, message = "In the race have to be no more than 6 cars.")
    private Set<Car> cars;*/

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "race_participants",
            joinColumns = @JoinColumn(name = "racer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Map<Racer, Car> participants = new HashMap<>();

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

    public Map<Racer, Car> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Racer, Car> participants) {
        this.participants = participants;
    }

    /*public Set<Racer> getRacers() {
        return racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }*/
}
