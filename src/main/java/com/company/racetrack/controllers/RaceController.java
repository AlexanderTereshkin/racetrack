package com.company.racetrack.controllers;

import com.company.racetrack.domain.*;
import com.company.racetrack.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public @ResponseBody Iterable<Race> getRaces() {
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
    public @ResponseBody Race getRace(@PathVariable(value ="id") Long id) {
        return raceRepository.findById(id).get();
    }

    /*MVC*/
    @GetMapping(path="/info/{id}")
    public String getRace(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("race", raceRepository.findById(id).get());
        return "info-race";
    }
    /*=================================================================*/


    /*=========================Add new race=========================*/
    /*REST API*/
    @PostMapping(path="/add-new-race/rest")
    public @ResponseBody String addNewRace(@RequestParam Track track) {
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
    public @ResponseBody String addNewParticipant(@RequestParam Race race, @RequestParam Racer racer, @RequestParam Car car) {
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
    public @ResponseBody String startRace(@PathVariable(value = "id") Long id) {
        Race race = raceRepository.findById(id).get();
        if (race.getStatus() == Status.CREATED && race.getRaceRacerCarLinkList().size() >= 3) {
            race.setStatus(Status.ONGOING);
            raceRepository.save(race);
            return "Race with id " + race.getId() + " was started.";
        } else {
            if (race.getStatus() != Status.CREATED) {
                return "Error: Race have status " + race.getStatus();
            }
            if (race.getRaceRacerCarLinkList().size() < 3) {
                return "Race could not start. Count of participants less than 3.";
            } else {
                return "Race could not start.";
            }
        }
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
    public @ResponseBody String finishRace(@PathVariable(value = "id") Long id) {
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
