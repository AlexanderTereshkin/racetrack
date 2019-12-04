package com.company.racetrack.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.company.racetrack.repositories.RaceRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Table(name="racers")
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please specify a name of this racer.")
    @Size(min = 1, message = "Please specify a name of this racer.")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "team_id", nullable = false)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @NotNull(message = "Please specify a team for this racer.")
    private Team team;

    @OneToMany(mappedBy = "racer", cascade = CascadeType.REMOVE)
    private List<RaceRacerCarLink> raceRacerCarLink = new ArrayList<>();

    public Racer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<RaceRacerCarLink> getRaceRacerCarLink() {
        return raceRacerCarLink;
    }

    public void setRaceRacerCarLink(List<RaceRacerCarLink> raceRacerCarLink) {
        this.raceRacerCarLink = raceRacerCarLink;
    }
}
