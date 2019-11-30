package com.company.racetrack.controllers;

import com.company.racetrack.domain.Car;
import com.company.racetrack.domain.Racer;
import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.CarRepository;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/cars")
public class CarController {

    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public CarController(CarRepository carRepository, TeamRepository teamRepository) {
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping(path="/all-cars")
    public @ResponseBody Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping(path="/add-new-car")
    public @ResponseBody String addNewCar(@RequestParam String brand, @RequestParam Long team_id) {
        Car car = new Car();
        car.setBrand(brand);
        Team team = teamRepository.findById(team_id).get();
        car.setTeam(team);
        team.getCarsList().add(car);
        carRepository.save(car);
        return "Saved new car: " + car.getBrand() + " " + car.getCarModel();
    }

    @GetMapping(path="/delete/{id}")
    public String deleteCar(@PathVariable(value ="id") Long id, Model model) {
        Car car = carRepository.findById(id).get();
        Team team = car.getTeam();
        team.getCarsList().remove(car);

        carRepository.deleteById(id);
        teamRepository.save(team);

        model.addAttribute("updateTeam", team);
        return "edit-team";
    }
}
