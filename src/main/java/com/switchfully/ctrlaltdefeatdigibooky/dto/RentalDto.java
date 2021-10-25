package com.switchfully.ctrlaltdefeatdigibooky.dto;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDto {
    private final String rentalId;
    private final String userId;
    private final String dateRented;
    private final String isbn;

    public RentalDto(String rentalId, String userId, String dateRented, String isbn) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.dateRented = dateRented;
        this.isbn = isbn;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDateRented() {
        return dateRented;
    }

    public String getIsbn() {
        return isbn;
    }
}
