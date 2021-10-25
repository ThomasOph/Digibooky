package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.dto.RentalDto;
import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    public RentalDto getRentalDto(Rental rental) {
        return new RentalDto(rental.getRentalId(),
                rental.getUserId(),
                rental.getDateRented().toString(),
                rental.getIsbn());
    }
}
