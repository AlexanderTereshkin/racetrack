package com.company.racetrack.controllers;

import com.company.racetrack.domain.Racer;
import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.RacerRepository;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/racers")
public class RacerController {

    private final RacerRepository racerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public RacerController(RacerRepository racerRepository, TeamRepository teamRepository) {
        this.racerRepository = racerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping()
    public String getAllRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }

    @GetMapping(path="/info/{id}")
    public String getRacer(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("racer", racerRepository.findById(id).get());
        return "info-racer";
    }

    @GetMapping(path="/add-new-racer")
    public String addNewRacer(Model model) {
        Racer newRacer = new Racer();
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("newRacer", newRacer);
        return "new-racer";
    }

    @PostMapping(path="/save-new-racer")
    public String saveNewRacer(@Valid @ModelAttribute("newRacer") Racer newRacer, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("teams", teamRepository.findAll());
            model.addAttribute("newRacer", newRacer);
            return "new-racer";
        }
        racerRepository.save(newRacer);
        teamRepository.save(newRacer.getTeam());
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }

    @GetMapping(path="/delete/{id}")
    public String deleteRacer(@PathVariable(value ="id") Long id, Model model) {
        Racer racer = racerRepository.findById(id).get();
        Team team = racer.getTeam();
        team.getRacersList().remove(racer);

        racerRepository.deleteById(id);
        teamRepository.save(team);

        model.addAttribute("updateTeam", team);
        return "edit-team";
    }
}
