package com.company.racetrack.controllers;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(path="/all-teams")
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @PostMapping(path="/add-new-team")
    public @ResponseBody String addNewTeam(@RequestParam String name) {
        Team team = new Team();
        team.setName(name);
        teamRepository.save(team);
        return "Team saved.";
    }
}
