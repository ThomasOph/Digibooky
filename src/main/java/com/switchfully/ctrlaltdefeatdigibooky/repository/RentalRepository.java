package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalRepository {
	private final List<Rental> rentals;

	public RentalRepository() {
		this.rentals = new ArrayList<>();
		rentals.add(new Rental("TestID","978 0 596 52068 7",
				  LocalDate.now().minusWeeks(3)));
		rentals.add(new Rental("TheLibrarian","978 0 596 52068 7",
				  LocalDate.now().minusWeeks(4)));
		rentals.add(new Rental("TheLibrarian","978 0 596 52068 7",
				  LocalDate.now().minusWeeks(42)));
	}

	public void addRental(Rental rental){
		rentals.add(rental);
	}

	public List<Rental> getRentals() {
		return rentals;
	}
}
