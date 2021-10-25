package com.switchfully.ctrlaltdefeatdigibooky.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
	private final UUID rentalId;
	private String userId;
	private String isbn;
	private LocalDate dateRented;

	public Rental(String userId, String isbn) {
		this(userId, isbn, LocalDate.now());
	}

	public Rental(String userId, String isbn, LocalDate dateRented) {
		this.rentalId = UUID.randomUUID();
		this.userId = userId;
		this.isbn = isbn;
		this.dateRented = dateRented;
	}

	public UUID getRentalId() {
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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Rental rental = ( Rental ) o;
		return rentalId.equals(rental.rentalId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rentalId);
	}
}
