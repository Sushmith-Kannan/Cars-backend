package com.casestudy.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;  // Correct import
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.springboot.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
//    Page<Rental> findByStatus(String status, Pageable pageable);  
//	Page<Rental> findByUserUsernameAndStatus(String username, String status, Pageable pageable);

	List<Rental> findByUserId(Long userId);
	
	Page<Rental> findAll(Pageable pageable);


	Page<Rental> findByStatus(String string, Pageable pageable);

	boolean existsByCarIdAndUserIdAndStartDateBeforeAndEndDateAfter(int carId, int userId, LocalDate endDate,
			LocalDate startDate);


	Page<Rental> findByUserIdAndStatus(int userId, String string, Pageable pageable);

	Page<Rental> findByUserId(int userId, Pageable pageable);

	List<Rental> findByUserId(int userId);

	
// Pagination support
}
