package com.company.racetrack.domain;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@NotNull(message = "You have to specify a brand of this car.")
    private String brand;

    //@NotNull(message = "You have to specify a model of this car.")
    private String carModel;

    //@NotNull(message = "You have to specify a engine power of this car.")
    private Float enginePower;

    @ManyToOne(targetEntity = Team.class)
    //@NotNull(message = "You have to specify a team for this car.")
    private Team team;

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
}
