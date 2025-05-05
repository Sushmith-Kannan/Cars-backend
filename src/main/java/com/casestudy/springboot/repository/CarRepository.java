package com.casestudy.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.Rental;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	Page<Car> findByStatusAndUserId(String status, int userId, Pageable pageable);


	List<Car> findByUserId(int userId);


}



