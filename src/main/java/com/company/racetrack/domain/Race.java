package com.company.racetrack.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "track_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull(message = "You must to choose a track, where this race will start.")
    private Track track;

    //private RaceRacerCarLink winner;

    @OneToMany(mappedBy = "race", cascade = CascadeType.REMOVE)
    @Size(min = 3, message = "In the race must be 3 or more participants.")
    private List<RaceRacerCarLink> raceRacerCarLinkList = new ArrayList<>();

    public Race() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<RaceRacerCarLink> getRaceRacerCarLinkList() {
        return raceRacerCarLinkList;
    }

    public void setRaceRacerCarLinkList(List<RaceRacerCarLink> raceRacerCarLinkList) {
        this.raceRacerCarLinkList = raceRacerCarLinkList;
    }
}
