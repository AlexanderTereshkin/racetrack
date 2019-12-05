package com.company.racetrack.controllers;

import com.company.racetrack.domain.Race;
import com.company.racetrack.domain.RaceRacerCarLink;
import com.company.racetrack.domain.Status;
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

    @GetMapping
    public String getRaces(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }

    @GetMapping(path="/info/{id}")
    public String getRace(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("race", raceRepository.findById(id).get());
        return "info-race";
    }


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

    //Add new participant to race
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

    //Add new participant to race
    @PostMapping(path="/save-new-participant")
    public String saveNewParticipant(@ModelAttribute RaceRacerCarLink newParticipant, Model model) {

        raceRacerCarLinkRepository.save(newParticipant);

        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }

    @GetMapping(path="/start/{id}")
    public String startRace(@PathVariable(value = "id") Long id, Model model) {
        Race race = raceRepository.findById(id).get();
        race.setStatus(Status.ONGOING);
        raceRepository.save(race);
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }

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
}
