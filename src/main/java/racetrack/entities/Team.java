package racetrack.entities;

import java.util.List;

public class Team {
    private int id;
    private List<Racer> racerList;
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
