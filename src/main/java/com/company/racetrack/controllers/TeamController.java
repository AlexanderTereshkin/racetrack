package com.company.racetrack.controllers;

import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    /*@GetMapping(path="/rest")
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }*/
    @GetMapping(path="/", consumes = "application/json")
    public ResponseEntity<Iterable<Team>> getAllTeams() {
        HttpHeaders responseHeaders = new HttpHeaders();
        //return new ResponseEntity<>(teamRepository.findAll(), responseHeaders, HttpStatus.OK);
        return ResponseEntity.ok().headers(responseHeaders).body(teamRepository.findAll());
    }

    @GetMapping(path="/page", consumes = "application/json")
    public ResponseEntity<Iterable<Team>> getAllTeams(@PageableDefault(page = 0, size = 2, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        //pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(teamRepository.findAll(pageable));
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
    @GetMapping(value = "/info/{id}", consumes = "application/json")
    @ResponseBody
    public Team getTeam(@PathVariable(value ="id") Long id) {
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
    @ResponseBody
    public Team addNewTeam(@RequestBody Team team/*@RequestParam String name*/) {
        /*Team team = new Team();
        team.setName(name);*/
        teamRepository.save(team);
        return team;
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
    @ResponseBody
    public Team editTeam(@PathVariable(value = "id") Long id, @RequestParam String name) {
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
    @ResponseBody
    public String deleteTeam(@PathVariable(value ="id") Long id) {
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
