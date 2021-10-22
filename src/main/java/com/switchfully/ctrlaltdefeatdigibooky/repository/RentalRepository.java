package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalRepository {
	private List<Rental> rentals;

	public RentalRepository() {
		this.rentals = new ArrayList<>();
	}

	public void addRental(Rental rental){
		rentals.add(rental);
	}

	public List<Rental> getRentals() {
		return rentals;
	}
}
