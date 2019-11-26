package racetrack.domain;

/*import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;*/

//@Entity
//@Table(name="racers")
public class Racer /*implements Serializable*/ {

    //private static final long serialVersionUID = 1L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    //@NotNull(message = "Please specify a name of this racer.")
    private final String name;

    //@ManyToOne(targetEntity = Team.class)
    private Team team;

    public Racer() {
        id = null;
        name = null;
    }

    public Racer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
