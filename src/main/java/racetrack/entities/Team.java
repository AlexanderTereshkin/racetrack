package racetrack.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(targetEntity = Racer.class)
    @Size(min = 3, max = 3, message = "In team must be a 3 racers.")
    private List<Racer> racerList;

    @NotNull(message = "You have to specify a name of this team.")
    private String name;

    public Team(int id, List<Racer> racerList, String name) {
        this.id = id;
        this.racerList = racerList;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Racer> getRacerList() {
        return racerList;
    }

    public void setRacerList(List<Racer> racerList) {
        this.racerList = racerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
