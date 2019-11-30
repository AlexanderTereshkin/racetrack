package com.company.racetrack.controllers;

import com.company.racetrack.domain.Racer;
import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.RacerRepository;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="/all-racers")
    public @ResponseBody Iterable<Racer> getAllRacers() {
        return racerRepository.findAll();
    }

    @PostMapping(path="/add-new-racer")
    public @ResponseBody String addNewRacer(@RequestParam String name, @RequestParam Long team_id) {
        Racer racer = new Racer();
        racer.setName(name);
        Team team = teamRepository.findById(team_id).get();
        racer.setTeam(team);
        team.getRacersList().add(racer);
        racerRepository.save(racer);
        return "Saved new racer: " + racer.getName();
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
