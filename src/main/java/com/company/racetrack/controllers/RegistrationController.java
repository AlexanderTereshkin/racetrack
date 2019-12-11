package com.company.racetrack.controllers;

import com.company.racetrack.repositories.UserRepository;
import com.company.racetrack.security.RegistrationForm;
import com.company.racetrack.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*REST API*/
    @PostMapping
    @ResponseBody
    public User registration(@RequestBody RegistrationForm registrationForm) {
        User user = registrationForm.toUser(passwordEncoder);
        userRepository.save(user);
        return user;
    }
}
