package racetrack.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="cars")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @NotNull(message = "You have to specify a brand of this car.")
    private final String brand;

    @NotNull(message = "You have to specify a model of this car.")
    private final String model;

    @NotNull(message = "You have to specify a engine power of this car.")
    private final Float enginePower;

    @ManyToOne(targetEntity = Team.class)
    @NotNull(message = "You have to specify a team for this car.")
    private Team team;

    private Car() {
        id = null;
        brand = null;
        model = null;
        enginePower = null;
    }

    public Car(Long id, String brand, String model, Float enginePower, Team team) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.enginePower = enginePower;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
