package racetrack.entities;

import java.util.List;

public class Race {
    private int id;
    private Status status;
    private Track track;
    private List<Racer> racerList;

    private enum Status {
        CREATED, ONGOING, FINISHED
    }
}
