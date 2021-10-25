package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(path = "return/{rentalId}")
	public String getRentalInformation(@PathVariable String rentalId){
		return rentalService.returnRental(rentalId);
	}


}
