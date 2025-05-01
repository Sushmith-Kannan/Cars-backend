package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public RentalDto getInProgressRentals(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rental> rentalPage = rentalService.getPaginatedProgressRentals(pageable);

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


    @PostMapping("/create")
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.createRental(
        		rental.getCar().getId(),
                rental.getUser().getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getCost(),
                rental.getStatus(),
                rental.getRating()
        );
    }
}
