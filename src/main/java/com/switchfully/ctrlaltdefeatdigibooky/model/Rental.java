package com.switchfully.ctrlaltdefeatdigibooky.model;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
	private final UUID rentalId;
	private String userId;
	private LocalDate dateRented;
	private String isbn;

	public Rental(String userId, String isbn) {
		this.rentalId = UUID.randomUUID();
		this.isbn = isbn;
		this.userId = userId;
		this.dateRented = LocalDate.now();
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
}
