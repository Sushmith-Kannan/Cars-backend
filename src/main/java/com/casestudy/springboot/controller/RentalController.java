package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.dto.RentalDto;
import com.casestudy.springboot.model.Rental;
import com.casestudy.springboot.service.RentalService;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = {"http://localhost:5173"})
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalDto rentalDto;

    @GetMapping("/active")
    public RentalDto getActiveRentals(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getPaginatedActiveRentals(pageable);

        rentalDto.setList(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }

    @GetMapping("/inprogress")
    public RentalDto getInProgressRentals(@RequestParam int page, @RequestParam int size, @RequestParam int userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getPaginatedProgressRentalsByUserId(userId, pageable);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setList(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }
    
    @GetMapping("/accepted")
    public RentalDto getAcceptedRentals(@RequestParam int page, @RequestParam int size, @RequestParam int userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getPaginatedAcceptedRentalsByUserId(userId, pageable);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setList(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }
    
    @GetMapping("/pending")
    public RentalDto getInPendingRentals(@RequestParam int page, @RequestParam int size, @RequestParam int userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getPaginatedPendingRentalsByUserId(userId, pageable);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setList(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }


    @GetMapping("/allrentals")
    public RentalDto getAllRentals(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getAllRentals(pageable);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setList(rentalPage.getContent());
        rentalDto.setTotalPages(rentalPage.getTotalPages());
        rentalDto.setTotalElements((int) rentalPage.getTotalElements());
        rentalDto.setSize(size);
        rentalDto.setCurrentPage(page);

        return rentalDto;
    }


    @GetMapping("user/{userId}")
    public Page<Rental> getRentalsByUserId(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return rentalService.getRentalsByUserId(userId, page, size);
    }

    @PostMapping("/create")
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.createRental(
        		rental.getCar().getId(),
                rental.getUser().getId(),
                rental.getStartDate(),
//                rental.getCost(),
                rental.getEndDate(),
                rental.getStatus(),
                rental.getRating()
        );
    }

    
    @PostMapping("/approve/{rentalId}")
    public ResponseEntity<Rental> approveRental(@PathVariable int rentalId) {
        Rental approveRental = rentalService.approvedRental(rentalId);
        return new ResponseEntity<>(approveRental, HttpStatus.OK);
    }

}
