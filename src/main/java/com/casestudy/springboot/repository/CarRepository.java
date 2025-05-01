package com.casestudy.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.springboot.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	List<Car> findByStatus(String status); 
}



