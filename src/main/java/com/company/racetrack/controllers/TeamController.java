package com.company.racetrack.controllers;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/teams")
public class TeamController {

    private TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @ModelAttribute(name = "team")
    public Team team() {
        return new Team();
    }

    @GetMapping
    public String getAllTeams(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }

    /*REST API*/
    @GetMapping(value = "/info/{id}/rest", headers="Accept=application/json")
    public @ResponseBody String getTeam(@PathVariable(value ="id") Long id) {
        Team team = teamRepository.findById(id).get();
        return team.toString();
    }

    @GetMapping(path="/info/{id}")
    public String getTeam(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("team", teamRepository.findById(id).get());
        return "info-team";
    }

    @GetMapping(path="/add-new-team")
    public String addNewTeam(Model model) {
        Team newTeam = new Team();
        model.addAttribute("newTeam", newTeam);
        return "new-team";
    }

    @PostMapping(path="/save-new-team")
    public String saveNewTeam(@Valid @ModelAttribute Team newTeam, Model model) {
        teamRepository.save(newTeam);
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }

    @GetMapping(path="/edit/{id}")
    public String editTeam(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("updateTeam", teamRepository.findById(id).get());
        return "edit-team";
    }

    @PostMapping(path="/info/{id}")
    public String saveEditTeam(@PathVariable(value = "id") Long id, @Valid @ModelAttribute Team updateTeam, Model model) {
        Team team = teamRepository.findById(id).get();
        team.setName(updateTeam.getName());
        teamRepository.save(team);
        model.addAttribute("team", team);
        return "info-team";
    }

    /*@DeleteMapping("/{id}")
    public @ResponseBody String deleteTeam(@PathVariable(value ="id") Long id) {
        teamRepository.deleteById(id);
        return "Team was deleted";
    }*/

    @GetMapping(path="/delete/{id}")
    public String deleteTeam(@PathVariable(value ="id") Long id, Model model) {
        teamRepository.deleteById(id);
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }
}
