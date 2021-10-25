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

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(path = "rent/{isbn}", produces =
			  MediaType.TEXT_PLAIN_VALUE)
	public RentalDto getRentalInformation( @PathVariable("isbn") String isbn,
	                                       @RequestHeader(value = "uuid",
			                                         required = false) String uuid){
		return rentalService.rent(isbn, uuid);
	}

	@GetMapping(path = "/overdue", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RentalDto> getAllRentalsOverdue(@RequestHeader(value = "uuid",
			  required = false) String uuid){
		return rentalService.getAllRentalsOverdue(uuid);
	}

}
