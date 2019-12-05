package com.company.racetrack.controllers;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    /*=========================Find all teams====================*/
    /*REST API*/
    @GetMapping(path="/rest")
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    /*MVC*/
    @GetMapping
    public String getAllTeams(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }
    /*===========================================================*/


    /*=========================Find team by ID====================*/
    /*REST API*/
    @GetMapping(value = "/info/{id}/rest")
    public @ResponseBody Team getTeam(@PathVariable(value ="id") Long id) {
        return teamRepository.findById(id).get();
    }
    /*MVC*/
    @GetMapping(path="/info/{id}")
    public String getTeam(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("team", teamRepository.findById(id).get());
        return "info-team";
    }
    /*===========================================================*/


    /*=========================Add new team======================*/
    /*REST API*/
    @PostMapping(path="/add-new-team/rest")
    public @ResponseBody String addNewTeam(@RequestParam String name) {
        Team team = new Team();
        team.setName(name);
        teamRepository.save(team);
        return "Team saved.";
    }

    /*MVC*/
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
    /*===========================================================*/


    /*=========================Edit team=========================*/
    /*REST API*/
    @PutMapping(path="/edit/{id}/rest")
    public @ResponseBody Team editTeam(@PathVariable(value = "id") Long id, @RequestParam String name) {
        Team team = teamRepository.findById(id).get();
        team.setName(name);
        teamRepository.save(team);
        return team;
    }

    /*MVC*/
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
    /*===========================================================*/


    /*=========================Delete team=========================*/
    /*REST API*/
    @DeleteMapping("/delete/{id}/rest")
    public @ResponseBody String deleteTeam(@PathVariable(value ="id") Long id) {
        teamRepository.deleteById(id);
        return "Team was deleted";
    }

    /*MVC*/
    @GetMapping(path="/delete/{id}")
    public String deleteTeam(@PathVariable(value ="id") Long id, Model model) {
        teamRepository.deleteById(id);
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }
    /*===========================================================*/
}
