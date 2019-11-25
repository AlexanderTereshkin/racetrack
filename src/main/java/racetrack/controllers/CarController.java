package racetrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import racetrack.data.CarRepository;
import racetrack.entities.Car;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
@SessionAttributes("team")
public class CarController {

    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/current")
    public String carInfo() {
        return "carInfo";
    }

    public String addCar(@Valid Car car, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "carInfo";
        }

        carRepository.save(car);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
