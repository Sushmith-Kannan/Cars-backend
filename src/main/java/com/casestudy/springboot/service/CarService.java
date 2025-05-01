package com.casestudy.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.repository.CarRepository;

@Service
public class CarService {


    @Autowired
    private CarRepository carRepository;

    public Car submitCarForApproval(Car car) {
        car.setStatus("submitted");
        return carRepository.save(car);
    }

    public Car approveCar(int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus("approved");
        return carRepository.save(car);
    }

    public List<Car> getApprovedCars() {
        return carRepository.findByStatus("approved");
    }

	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		 return carRepository.findAll();
	}

	public Optional<Car> getCarById(int carId) {
		// TODO Auto-generated method stub
		return carRepository.findById(carId);
	}
}
