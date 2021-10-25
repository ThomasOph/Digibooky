package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.RentalDto;
import com.switchfully.ctrlaltdefeatdigibooky.exceptions.BookNotFoundException;
import com.switchfully.ctrlaltdefeatdigibooky.exceptions.UserNotFoundException;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.RentalMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import com.switchfully.ctrlaltdefeatdigibooky.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {
	private final RentalRepository rentalRepository;
	private final UserService userService;
	private final BookService bookService;
	private final RentalMapper rentalMapper;

	public RentalService(RentalRepository rentalRepository, UserService userService,
	                     BookService bookService, RentalMapper rentalMapper) {
		this.rentalRepository = rentalRepository;
		this.userService = userService;
		this.bookService = bookService;
		this.rentalMapper = rentalMapper;
	}

	public RentalDto rent(String userId, String isbn){
		if (userService.getUser(userId) == null) throw new UserNotFoundException();
		if (bookService.getBookByISBN(isbn) == null) throw new BookNotFoundException();

		if (!isStillInStock(isbn)) throw new BookNotFoundException();
		Rental renting = new Rental(userId,isbn);
		rentalRepository.addRental(renting);

		return rentalMapper.getRentalDto(renting);
	}
	private boolean isStillInStock(String isbn){
		return amountOfRentals(isbn) < amountOfBooksLeft(isbn);
	}

	private int amountOfBooksLeft(String isbn){
		return bookService.getBooksByISBN(isbn).size();
	}
	private int amountOfRentals(String isbn){
		return rentalRepository.getRentals().stream()
				  .filter(rental -> rental.getIsbn().equals(isbn))
				  .toList().size();
	}

	public List<String> getUsersRentingBook(String isbn){
		return rentalRepository.getRentals().stream()
				  .filter(rental -> rental.getIsbn().equals(isbn))
				  .map(rental -> userService.getUserDetails(rental.getUserId()))
				  .toList();
	}
}
