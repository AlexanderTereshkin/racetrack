package com.company.racetrack.controllers;

import com.company.racetrack.log.LogCustom;
import com.company.racetrack.repositories.LogCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LogCustomService {
    @Autowired
    private LogCustomRepository logCustomRepository;



    public void setLog(String message) {
        LogCustom logCustom = new LogCustom();
        logCustom.setMessage(new Date() + " Log: " + message);
        logCustomRepository.save(logCustom);
    }
}
