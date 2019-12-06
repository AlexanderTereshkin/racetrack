package com.company.racetrack.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "You have to specify a name of this team.")
    @Size(max = 25, message = "Name of the team must be no more than 25 chars.")
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
    @JsonIdentityReference(alwaysAsId = true)
    @Size(max = 3, message = "No more than 3 riders per team.")
    private List<Racer> racersList = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Car> carsList = new ArrayList<>();

    public Team() {
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

    public List<Racer> getRacersList() {
        return racersList;
    }

    public void setRacersList(List<Racer> racersList) {
        this.racersList = racersList;
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }
}
