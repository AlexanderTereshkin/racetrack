package com.company.racetrack.controllers;

import com.company.racetrack.domain.Car;
import com.company.racetrack.domain.Team;
import com.company.racetrack.repositories.CarRepository;
import com.company.racetrack.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /*=========================Find all cars====================*/
    /*REST API*/
    @GetMapping(path="/rest")
    public @ResponseBody Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    /*MVC*/
    @GetMapping()
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "cars";
    }
    /*===========================================================*/


    /*=========================Find car by ID====================*/
    /*REST API*/
    @GetMapping(value = "/info/{id}/rest")
    public @ResponseBody Car getCar(@PathVariable(value ="id") Long id) {
        return carRepository.findById(id).get();
    }

    /*MVC*/
    @GetMapping(path="/info/{id}")
    public String getCar(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "info-car";
    }
    /*===========================================================*/


    /*=========================Add new car=======================*/
    /*REST API*/
    @PostMapping(path="/add-new-car/rest", consumes = "application/json", produces = "application/json")
    public @ResponseBody Car addNewCar(@RequestBody Car car /*@RequestParam String brand, @RequestParam String carModel, @RequestParam Float enginePower, @RequestParam Team team*/) {
        /*Car newCar = new Car();
        newCar.setBrand(brand);
        newCar.setCarModel(carModel);
        newCar.setEnginePower(enginePower);
        newCar.setTeam(team);*/
        carRepository.save(car);
        return car;
    }

    /*MVC*/
    @GetMapping(path="/add-new-car")
    public String addNewCar(Model model) {
        Car newCar = new Car();
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("newCar", newCar);
        return "new-car";
    }

    @PostMapping(path="/save-new-car")
    public String saveNewCar(@Valid @ModelAttribute Car newCar, Model model, Errors errors) {
        if (errors.hasErrors()) {
            return "new-car";
        }
        carRepository.save(newCar);
        model.addAttribute("cars", carRepository.findAll());
        return "cars";
    }
    /*===========================================================*/


    /*=========================Delete car========================*/
    /*REST API*/
    @DeleteMapping("/delete/{id}/rest")
    public @ResponseBody String deleteCar(@PathVariable(value ="id") Long id) {
        carRepository.deleteById(id);
        return "Car was deleted";
    }

    /*MVC*/
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
    /*===========================================================*/
}
