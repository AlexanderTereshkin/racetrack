package com.company.racetrack.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@ConditionalOnProperty(name = "logAdditional", havingValue = "logToConsole")
public class LogToConsole implements LogAdditional {

    private static LogToConsole instance;

    private LogToConsole() {
    }

    public static LogToConsole getInstance() {
        if (instance == null) {
            instance = new LogToConsole();
        }

        return instance;
    }

    @Override
    public void logTo(String message) {
        System.out.println("Date: " + new Date() + " Message: " + message);
    }
}
