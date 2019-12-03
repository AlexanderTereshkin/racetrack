package com.company.racetrack.controllers;

import com.company.racetrack.repositories.RaceRacerCarLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/results")
public class RaceRacerCarLinkController {

    private final RaceRacerCarLinkRepository raceRacerCarLinkRepository;

    @Autowired
    public RaceRacerCarLinkController(RaceRacerCarLinkRepository raceRacerCarLinkRepository) {
        this.raceRacerCarLinkRepository = raceRacerCarLinkRepository;
    }
}
