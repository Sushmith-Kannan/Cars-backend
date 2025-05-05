package com.casestudy.springboot.service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.Rental;
import com.casestudy.springboot.model.User;
import com.casestudy.springboot.repository.AuthRepository;
import com.casestudy.springboot.repository.CarRepository;
import com.casestudy.springboot.repository.RentalRepository;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;
    
	@Autowired
    private AuthRepository userRepo;


    public Page<Rental> getPaginatedActiveRentals(Pageable pageable) {
        return rentalRepository.findByStatus("Completed", pageable); 
    }
    public Page<Rental> getAllRentals(Pageable pageable) {
        return rentalRepository.findAll(pageable);
    }

//    public Rental createRental(Integer carId, LocalDate startDate, LocalDate endDate, Double cost, String status, Double rating) {
//        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
//        Rental rental = new Rental();
//        rental.setCar(car);
//        rental.setStartDate(startDate);
//        rental.setEndDate(endDate);
//        rental.setCost(cost);
//        rental.setStatus(status);
//        rental.setRating(rating);
//        return rentalRepository.save(rental);
//    }
	public Page<Rental> getPaginatedProgressRentals(Pageable pageable) {
		// TODO Auto-generated method stub
		  return rentalRepository.findByStatus("In Progress", pageable);
	}
	

    public Rental createRental(int carId, int userId, LocalDate startDate, LocalDate endDate,  String status, double rating) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Rental rental = new Rental();
        rental.setCar(car);
        rental.setUser(user);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
//        rental.setCost(cost);
        rental.setStatus(status);
        rental.setRating(rating);

        return rentalRepository.save(rental);
    }
	public Page<Rental> getPaginatedProgressRentalsByUserId(int userId, Pageable pageable) {
		return rentalRepository.findByUserIdAndStatus(userId, "In Progress", pageable);
	}
	public Page<Rental> getRentalsByUserId(int userId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
        return rentalRepository.findByUserId(userId, pageable);
	}
	public List<Car> getCarsByUserId(int userId) {
	
		List<Rental> rentals = rentalRepository.findByUserId(userId);

        List<Car> cars = rentals.stream()
            .map(Rental::getCar)
            .collect(Collectors.toList());

        return cars;
	}
	public Page<Rental> getPaginatedPendingRentalsByUserId(int userId, Pageable pageable) {
		return rentalRepository.findByUserIdAndStatus(userId, "pending", pageable);
	}

	public Rental approvedRental(int rentalId) {
		Rental rental = rentalRepository.findById(rentalId)
	            .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rentalId));

	        rental.setStatus("APPROVED"); 
	        return rentalRepository.save(rental);
	}
	
	public Page<Rental> getPaginatedAcceptedRentalsByUserId(int userId, Pageable pageable) {
		return rentalRepository.findByUserIdAndStatus(userId, "APPROVED", pageable);
	}

}
