package com.company.racetrack.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "You have to specify a brand of this car.")
    private String brand;

    @NotNull(message = "You have to specify a model of this car.")
    private String carModel;

    @NotNull(message = "You have to specify a engine power of this car.")
    private Float enginePower;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "team_id", nullable = false)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
    //@JsonIdentityReference(alwaysAsId = true)
    @NotNull(message = "You have to specify a team for this car.")
    private Team team;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<RaceRacerCarLink> raceRacerCarLink = new ArrayList<>();

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
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
