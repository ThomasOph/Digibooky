package com.switchfully.ctrlaltdefeatdigibooky.dto;

import java.time.LocalDate;
import java.util.UUID;

public record RentalDto
		  (String rentalId, String userId,String dateRented, String isbn){}
