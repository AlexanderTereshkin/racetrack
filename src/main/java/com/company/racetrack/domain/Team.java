package com.company.racetrack.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@NotNull(message = "You have to specify a name of this team.")
    private String name;

    //@OneToMany(targetEntity = Racer.class)
    //@Size(min = 3, max = 3, message = "In team must be a 3 racers.")
    //private List<Racer> racerList;

    @OneToMany(targetEntity = Car.class)
    //@Size(min = 3, max = 3, message = "In team must be a 3 racers.")
    private List<Car> carsList = new ArrayList<>();

    protected Team() {
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

    /*public List<Racer> getRacerList() {
        return racerList;
    }

    public void setRacerList(List<Racer> racerList) {
        this.racerList = racerList;
    }*/

    public List<Car> getCarsList() {
        return carsList;
    }

    public void addCarToTeam(Car car) {
        this.carsList.add(car);
    }
}
