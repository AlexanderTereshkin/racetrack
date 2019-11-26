package racetrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import racetrack.domain.Car;
import racetrack.repositories.CarRepository;

@Controller
@RequestMapping(path="/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping(path="/all-cars")
    public @ResponseBody Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping(path="/add-new-car")
    public @ResponseBody String addNewCar(@RequestParam String brand) {
        Car car = new Car();
        car.setBrand(brand);
        carRepository.save(car);
        return "Saved";
    }
}
