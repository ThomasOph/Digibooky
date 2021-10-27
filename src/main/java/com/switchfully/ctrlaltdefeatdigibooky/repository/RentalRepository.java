package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalRepository {
    private final List<Rental> rentals;

    public RentalRepository() {
        this.rentals = new ArrayList<>();
        rentals.add(new Rental("TheAdmin", "9790856451659", LocalDate.now().minusWeeks(3)));
        rentals.add(new Rental("Thomas", "9791806461653", LocalDate.now().minusWeeks(4)));
        rentals.add(new Rental("TheLibrarian", "9790856451659", LocalDate.now().minusWeeks(6)));
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
