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
		rentals.add(new Rental("TestID",
				  "978 0 596 52068 7"));
		rentals.add(new Rental("TheLibrarian","978 0 596 52068 7" ));
		rentals.add(new Rental("TheLibrarian","978 0 596 52068 7" ));
	}

	public void addRental(Rental rental){
		rentals.add(rental);
	}

	public List<Rental> getRentals() {
		return rentals;
	}
}
