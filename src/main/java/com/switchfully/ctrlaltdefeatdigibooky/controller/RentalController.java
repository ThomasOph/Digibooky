package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.dto.RentalDto;
import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import com.switchfully.ctrlaltdefeatdigibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "rentals")
@RestController
public class RentalController {

	private final RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RentalDto> getRentalsFromMemberId(@PathVariable String memberId){
		List<RentalDto> rentalsOfMember = rentalService.getRentalsFromMember(memberId);
		if (rentalsOfMember.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not found!");
		return rentalsOfMember;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "return/{rentalId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getRentalInformation(@PathVariable("rentalId") String rentalId){
		return rentalService.returnRental(rentalId);
	}


}
