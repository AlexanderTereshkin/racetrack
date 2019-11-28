package com.company.racetrack.controllers;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(path="/all-teams")
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Team getTeamById(@PathVariable(value = "id") Long id) {
        return teamRepository.findById(id).get();
    }

    @PostMapping(path="/add-new-team")
    public @ResponseBody String addNewTeam(@RequestParam String name) {
        Team team = new Team();
        team.setName(name);
        teamRepository.save(team);
        return "Team saved.";
    }

    @PutMapping(path="/{id}")
    public @ResponseBody String updateTeam(@PathVariable(value = "id") Long id, @Valid @RequestBody Team updateTeam) {
        Team team = teamRepository.findById(id).get();
        team.setName(updateTeam.getName());
        teamRepository.save(team);
        return "Team was updated";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteTeam(@PathVariable(value ="id") Long id) {
        teamRepository.deleteById(id);
        return "Team was deleted";
    }
}
