package com.casestudy.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.User;
import com.casestudy.springboot.service.CarService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CarController {

    @Autowired
    private CarService carService;

    // POST API to add a car for approval
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        // Fetch the currently authenticated user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();  // This assumes you have a UserDetails implementation

        // Set the authenticated user to the car
        car.setUser(user);

        // Submit the car for approval (assuming this method handles the logic)
        Car submittedCar = carService.submitCarForApproval(car);
        return new ResponseEntity<>(submittedCar, HttpStatus.CREATED);
    }

    // POST API to approve a car by the manager
    @PostMapping("/approve/{carId}")
    public ResponseEntity<Car> approveCar(@PathVariable int carId) {
        // Approve the car with the given ID
        Car approvedCar = carService.approveCar(carId);
        return new ResponseEntity<>(approvedCar, HttpStatus.OK);
    }

    // GET API to retrieve all approved cars
    @GetMapping("/approved")
    public ResponseEntity<List<Car>> getApprovedCars() {
        // Fetch all approved cars
        List<Car> approvedCars = carService.getApprovedCars();
        return new ResponseEntity<>(approvedCars, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        // Fetch all approved cars
        List<Car> allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }
    @GetMapping("/{carId}")
    public Optional<Car> getCarById(@PathVariable int carId) {
        Optional<Car> car = carService.getCarById(carId);
		return car;
      
    }

}
