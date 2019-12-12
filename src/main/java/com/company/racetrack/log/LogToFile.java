package com.company.racetrack.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Service
@ConditionalOnProperty(name = "logAdditional", havingValue = "logToFile")
public class LogToFile implements LogAdditional {

    private static LogToFile instance;
    private final String path;

    private LogToFile() {
        this.path = "C:\\Users\\ateresh\\IdeaProjects\\racetrack\\src\\main\\resources\\static\\logs.txt";
    }

    public static LogToFile getInstance() {
        if (instance == null) {
            instance = new LogToFile();
        }

        return instance;
    }

    @Override
    public void logTo(String message) {
        String fullMessage = "Date: " + new Date() + " Message: " + message + "\n";
        try {
            Files.write(Paths.get(path), fullMessage.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }
}
