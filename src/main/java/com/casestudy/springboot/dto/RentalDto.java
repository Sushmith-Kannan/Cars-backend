package com.casestudy.springboot.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.casestudy.springboot.model.Car;
import com.casestudy.springboot.model.Rental;

@Component
public class RentalDto {
    private List<Rental> list;
    private List<Car> list1;
    private int totalPages;
    private int totalElements;
    private int size;
    private int currentPage;

    public List<Rental> getList() {
        return list;
    }

    public void setList(List<Rental> list) {
        this.list = list;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

	public List<Car> getList1() {
		return list1;
	}

	public void setList1(List<Car> list1) {
		this.list1 = list1;
	}

    
}
