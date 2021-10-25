package com.switchfully.ctrlaltdefeatdigibooky.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
    private final String rentalId;
    private final String userId;
    private final String isbn;
    private final LocalDate dateRented;

    public Rental(String userId, String isbn) {
        this(userId, isbn, LocalDate.now());
    }

    public Rental(String userId, String isbn, LocalDate dateRented) {
        this.rentalId = UUID.randomUUID().toString();
        this.userId = userId;
        this.isbn = isbn;
        this.dateRented = dateRented;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Rental rental = (Rental) o;
        return rentalId.equals(rental.rentalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }

}
