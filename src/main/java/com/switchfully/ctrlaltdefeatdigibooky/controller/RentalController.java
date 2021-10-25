package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/rentals")
@RestController
public class RentalController {

	private final RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping(path = "return/{rentalId}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getRentalInformation(@PathVariable("rentalId") String rentalId){
		return rentalService.returnRental(rentalId);
	}


}
