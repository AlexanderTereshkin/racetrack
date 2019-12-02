package com.company.racetrack.controllers;

import com.company.racetrack.domain.Race;
import com.company.racetrack.repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/races")
public class RaceController {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @GetMapping(path="/add-new-race")
    public String addNewRace(Model model) {
        Race newRace = new Race();
        model.addAttribute("newRace", newRace);
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



}
