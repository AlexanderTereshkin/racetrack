package com.company.racetrack.controllers;

import com.company.racetrack.domain.*;
import com.company.racetrack.error.RacerNowBusyInAnotherRaceException;
import com.company.racetrack.error.StartRaceFailedException;
import com.company.racetrack.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/races")
public class RaceController {

    private final RaceRepository raceRepository;
    private final RaceRacerCarLinkRepository raceRacerCarLinkRepository;
    private final TrackRepository trackRepository;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;

    @Autowired
    public RaceController(RaceRepository raceRepository,
                          RaceRacerCarLinkRepository raceRacerCarLinkRepository,
                          TrackRepository trackRepository,
                          RacerRepository racerRepository,
                          CarRepository carRepository) {
        this.raceRepository = raceRepository;
        this.raceRacerCarLinkRepository = raceRacerCarLinkRepository;
        this.trackRepository = trackRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }

    /*=========================Find all races=========================*/
    /*REST API*/
    @GetMapping(path="/rest")
    @ResponseBody
    public Iterable<Race> getRaces() {
        return raceRepository.findAll();
    }

    /*MVC*/
    @GetMapping
    public String getRaces(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }
    /*================================================================*/


    /*=========================Find race by ID=========================*/
    /*REST API*/
    @GetMapping(value = "/info/{id}/rest")
    @ResponseBody
    public Race getRace(@PathVariable(value ="id") Long id) {
        return raceRepository.findById(id).get();
    }

    /*MVC*/
    @GetMapping(path="/info/{id}")
    public String getRace(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("race", raceRepository.findById(id).get());
        return "info-race";
    }
    /*=================================================================*/

    /*=========================Find all races for one racer=========================*/
    /*REST API*/
    @GetMapping(path="/list-by-racer/{id}", consumes = "application/json")
    @ResponseBody
    public Iterable<Race> getRacesForRacer(@PathVariable(value = "id") Long id) {
        List<Race> raceList = new ArrayList<>();
        for (RaceRacerCarLink r : raceRacerCarLinkRepository.findRacesByRacer(racerRepository.findById(id).get())) {
            raceList.add(r.getRace());
        }
        return raceList;
    }
    /*==============================================================================*/


    /*=========================Find all races for one racer by one car=========================*/
    /*REST API*/
    @GetMapping(path="/list-by-racer-by-car/rest")
    @ResponseBody
    public Iterable<Race> getRacesForRacer(@RequestParam Racer racer, @RequestParam Car car) {
        List<Race> raceList = new ArrayList<>();
        for (RaceRacerCarLink r : raceRacerCarLinkRepository.findRacesByRacerByCar(racer, car)) {
            raceList.add(r.getRace());
        }
        return raceList;
    }
    /*==============================================================================*/


    /*=========================Add new race=========================*/
    /*REST API*/
    @PostMapping(path="/add-new-race/rest")
    @ResponseBody
    public String addNewRace(@RequestParam Track track) {
        Race race = new Race();
        race.setTrack(track);
        race.setStatus(Status.CREATED);
        raceRepository.save(race);
        return "Race saved.";
    }

    /*MVC*/
    @GetMapping(path="/add-new-race")
    public String addNewRace(Model model) {
        Race newRace = new Race();
        model.addAttribute("newRace", newRace);
        model.addAttribute("tracks", trackRepository.findAll());
        return "new-race";
    }

    @PostMapping(path="/register-new-race")
    public String registerNewRace(@Valid @ModelAttribute("newRace") Race newRace, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("newRace", newRace);
            return "new-race";
        }
        newRace.setStatus(Status.CREATED);
        raceRepository.save(newRace);
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }
    /*=============================================================*/


    /*=========================Add new participant to race=========================*/
    /*REST API*/
    @PostMapping(path="/add-new-participant/rest")
    @ResponseBody
    public String addNewParticipant(@RequestParam Race race, @RequestParam Racer racer, @RequestParam Car car) {
        if (raceRacerCarLinkRepository.findRacerByRace(race, racer) == racer.getId()) {
            return "This racer is already set to this race. Choose another.";
        }
        if (raceRacerCarLinkRepository.findCarByRace(race, car) == car.getId()) {
            return "This car is already set to this race. Choose another.";
        }
        if (racer.getTeam() != car.getTeam()) {
            return "Racer " + racer.getName() + " from team " + racer.getTeam().getName() + ". But car from team " + car.getTeam().getName() + ". Choose car for this racer, which belong for his team.";
        }
        RaceRacerCarLink newParticipant = new RaceRacerCarLink();
        newParticipant.setRace(race);
        newParticipant.setRacer(racer);
        newParticipant.setCar(car);
        raceRacerCarLinkRepository.save(newParticipant);
        return "New participant was added to race with id " + race.getId();
    }

    /*MVC*/
    @GetMapping(path="/edit/{id}")
    public String addRacerToRace(@PathVariable(value = "id") Long id, Model model) {
        Race race = raceRepository.findById(id).get();
        RaceRacerCarLink newParticipant = new RaceRacerCarLink();
        model.addAttribute("newParticipant", newParticipant);
        model.addAttribute("race", race);
        model.addAttribute("races", raceRepository.findAll());
        model.addAttribute("racers", racerRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        return "edit-race";
    }

    @PostMapping(path="/save-new-participant")
    public String saveNewParticipant(@ModelAttribute RaceRacerCarLink newParticipant, Model model) {

        raceRacerCarLinkRepository.save(newParticipant);

        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }
    /*============================================================================*/


    /*=========================Start the race=========================*/
    /*REST API*/
    @PutMapping(path="/start/{id}/rest")
    public ResponseEntity<Race> startRace(@PathVariable(value = "id") Long id) throws StartRaceFailedException{
        Race race = raceRepository.findById(id).get();

        if (race.getStatus() != Status.CREATED) {
            throw new StartRaceFailedException("Race could not start. Because it`s status is " + race.getStatus());
        }

        List<Racer> racersOfThisRace = new ArrayList<>();

        for (RaceRacerCarLink r : race.getRaceRacerCarLinkList()) {
            racersOfThisRace.add(r.getRacer());
        }

        for (Racer r : racersOfThisRace) {
            for (RaceRacerCarLink raceRacerCarLink : raceRacerCarLinkRepository.findByRacer(r)) {
                if (raceRacerCarLink.getRace().getStatus() == Status.ONGOING) {
                    throw new RacerNowBusyInAnotherRaceException("Racer " + r.getName() + " now is participating in another race with id "
                            + raceRacerCarLink.getRace().getId() + " which is not finished yet.");
                }
            }
        }

        race.setStatus(Status.ONGOING);
        raceRepository.save(race);
        return new ResponseEntity<>(race, HttpStatus.OK);
    }

    /*MVC*/
    @GetMapping(path="/start/{id}")
    public String startRace(@PathVariable(value = "id") Long id, Model model) {
        Race race = raceRepository.findById(id).get();
        race.setStatus(Status.ONGOING);
        raceRepository.save(race);
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }
    /*================================================================*/


    /*=========================Finish the race=========================*/
    /*REST API*/
    @PutMapping(path="/finish/{id}/rest")
    @ResponseBody
    public String finishRace(@PathVariable(value = "id") Long id) {
        Race race = raceRepository.findById(id).get();

        if (race.getStatus() == Status.ONGOING) {
            for (RaceRacerCarLink r : race.getRaceRacerCarLinkList()) {
                r.setResultTime(Math.round(Math.random() * 100));  // randomly set results for participants
            }
            race.setStatus(Status.FINISHED);
            raceRepository.save(race);
            return "Race with id " + race.getId() + " was finished.";
        } else {
            return "Error: Race have status " + race.getStatus();
        }
    }

    /*MVC*/
    @GetMapping(path="/finish/{id}")
    public String finishRace(@PathVariable(value = "id") Long id, Model model) {
        Race race = raceRepository.findById(id).get();

        //race.getRaceRacerCarLinkList().stream().map(r -> r.getResultTime() + Math.round(Math.random() * 100));

        for (RaceRacerCarLink r : race.getRaceRacerCarLinkList()) {
            r.setResultTime(Math.round(Math.random() * 100));
        }

        race.setStatus(Status.FINISHED);
        raceRepository.save(race);
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }
    /*=================================================================*/
}
