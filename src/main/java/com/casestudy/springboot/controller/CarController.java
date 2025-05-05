package com.casestudy.springboot.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.casestudy.springboot.dto.RentalDto;
import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.Rental;
import com.casestudy.springboot.model.User;
import com.casestudy.springboot.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = {"http://localhost:5173"})
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();  
        car.setUser(user);
        Car submittedCar = carService.submitCarForApproval(car);
        return new ResponseEntity<>(submittedCar, HttpStatus.CREATED);
    }

    // POST API to approve a car by the manager
    @PostMapping("/approve/{carId}")
    public ResponseEntity<Car> approveCar(@PathVariable int carId) {
        Car approvedCar = carService.approveCar(carId);
        return new ResponseEntity<>(approvedCar, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }
    
    @GetMapping("/{carId}")
    public Optional<Car> getCarById(@PathVariable int carId) {
        Optional<Car> car = carService.getCarById(carId);
		return car;
      
    }
    @GetMapping("byid/{userId}")
    public List<Car> getCarsByUserId(@PathVariable int userId) {
        List<Car> cars = carService.getCarsByUserId(userId);
        return cars;
    }

    @GetMapping("/models")
    public ResponseEntity<Object> getCarModels() {
        return ResponseEntity.ok(carService.getDistinctCarModels());
    }
    
    @GetMapping("/submitted")
    public RentalDto getInPendingRentals(@RequestParam int page, @RequestParam int size, @RequestParam int userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> rentalPage = carService.getPaginatedPendingRentalsByUserId(userId, pageable);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setList1(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }

}
