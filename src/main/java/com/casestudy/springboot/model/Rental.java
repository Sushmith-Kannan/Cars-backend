package com.casestudy.springboot.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rental {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Car car;
    
    
    @ManyToOne
	private User user;

    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private LocalDate startDate;
    private LocalDate endDate;
//    private Double cost;
    private String status;
    private Double rating=0.0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
//	public Double getCost() {
//		return cost;
//	}
//	public void setCost(Double cost) {
//		this.cost = cost;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
    
    
}
