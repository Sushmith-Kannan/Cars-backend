package com.casestudy.springboot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.Rental;
import com.casestudy.springboot.model.User;
import com.casestudy.springboot.repository.AuthRepository;
import com.casestudy.springboot.repository.CarRepository;

@Service
public class CarService {


    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private AuthRepository authrepo;

    public Car submitCarForApproval(Car car) {
        car.setStatus("submitted");
        return carRepository.save(car);
    }

    public Car approveCar(int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus("approved");
        return carRepository.save(car);
    }

	public List<Car> getAllCars() {

		 return carRepository.findAll();
	}

	public Optional<Car> getCarById(int carId) {
		return carRepository.findById(carId);
	}


	public Object getDistinctCarModels() {
		   return carRepository.findAll().stream()
	                .map(Car::getCarModel)
	                .filter(Objects::nonNull)
	                .distinct()
	                .collect(Collectors.toList());
	}

	public List<Car> getCarsByUserId(int userId) {
		User user = authrepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    List<Car> cars = carRepository.findByUserId(userId);  
	    if (cars.isEmpty()) {
	        throw new RuntimeException("No cars found for this user");
	    }

	    return cars;	}
	
	public Page<Car> getPaginatedPendingRentalsByUserId(int userId, Pageable pageable) {
	    return carRepository.findByStatusAndUserId("submitted", userId, pageable);
	}



}
