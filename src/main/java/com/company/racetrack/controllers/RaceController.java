package com.company.racetrack.controllers;

import com.company.racetrack.domain.Race;
import com.company.racetrack.domain.RaceRacerCarLink;
import com.company.racetrack.repositories.RaceRacerCarLinkRepository;
import com.company.racetrack.repositories.RaceRepository;
import com.company.racetrack.repositories.TrackRepository;
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

    @Autowired
    public RaceController(RaceRepository raceRepository, RaceRacerCarLinkRepository raceRacerCarLinkRepository, TrackRepository trackRepository) {
        this.raceRepository = raceRepository;
        this.raceRacerCarLinkRepository = raceRacerCarLinkRepository;
        this.trackRepository = trackRepository;
    }

    @GetMapping
    public String getRaces(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "races";
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
        raceRepository.save(newRace);
        model.addAttribute("races", raceRepository.findAll());
        return "races";
    }


    @GetMapping(path="/edit/{id}")
    public String addRacerToRace(@PathVariable(value = "id") Long id, Model model) {
        Race race = raceRepository.findById(id).get();
        RaceRacerCarLink raceRacerCarLink = new RaceRacerCarLink();
        model.addAttribute("newParticipant", raceRacerCarLink);
        model.addAttribute("race", race);
        return "edit-race";
    }

    @PostMapping(path="/info/{id}")
    public String saveNewParticipant(@PathVariable(value = "id") Long id, @ModelAttribute Race race, @ModelAttribute RaceRacerCarLink newParticipant, Model model) {
        //Race race = raceRepository.findById(id).get();


        return "info-race";
    }
}
