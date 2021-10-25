package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.RentalDto;
import com.switchfully.ctrlaltdefeatdigibooky.exceptions.BookNotFoundException;
import com.switchfully.ctrlaltdefeatdigibooky.exceptions.UserNotFoundException;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.RentalMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Rental;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import com.switchfully.ctrlaltdefeatdigibooky.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    public static final int RENT_OVERDUE_THRESHOLD = 3;
    private final Logger logger = LoggerFactory.getLogger(RentalService.class);

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

    public RentalDto rent(String userId, String isbn) {
        if (userService.getUser(userId) == null) {
            logger.warn("User not found when trying to rent a book");
            throw new UserNotFoundException();
        }
        if (bookService.getBookByISBN(isbn) == null) {
            logger.warn("User tried to look for ISBN " + isbn + ", but none found.");
            throw new BookNotFoundException();
        }
        if (!isStillInStock(isbn)) {
            logger.warn("User tried to rent a book that's not in stock anymore.");
            throw new BookNotFoundException();
        }

        Rental renting = new Rental(userId, isbn);
        rentalRepository.addRental(renting);

        logger.info("Added rental " + renting.getRentalId());
        return rentalMapper.getRentalDto(renting);
    }

    private boolean isStillInStock(String isbn) {
        return amountOfRentals(isbn) < amountOfBooksLeft(isbn);
    }

    private int amountOfBooksLeft(String isbn) {
        return bookService.getBookByISBN(isbn).getCopiesOfBook();
    }

    private int amountOfRentals(String isbn) {
        return rentalRepository.getRentals().stream()
                .filter(rental -> rental.getIsbn().equals(isbn))
                .toList().size();
    }

    public List<String> getUsersRentingBook(String isbn) {
        return rentalRepository.getRentals().stream()
                .filter(rental -> rental.getIsbn().equals(isbn))
                .map(rental -> userService.getUserDetails(rental.getUserId()))
                .toList();
    }

    public String returnRental(String rentalId) {
        Rental toReturn = getRentalById(rentalId);
        if (toReturn == null) {
            return "This rentalId was not found in our library";
        }

        rentalRepository.getRentals().remove(toReturn);
        logger.info("Removed rental " + rentalId);

        return isBookOverdue(toReturn.getDateRented()) ?
                "This book is late" : "Your book is on time";
    }

    private boolean isBookOverdue(LocalDate rentalDate) {
        return rentalDate.plusWeeks(RENT_OVERDUE_THRESHOLD)
                .isBefore(LocalDate.now());
    }

    public Rental getRentalById(String rentalId) {
        return rentalRepository.getRentals().stream()
                .filter(rental -> rental.getRentalId().equals(rentalId))
                .findFirst().orElse(null);
    }

    public List<RentalDto> getRentalsFromMember(String userId) {
        return rentalRepository.getRentals().stream()
                .filter(member -> member.getUserId().equals(userId))
                .map(rentalMapper::getRentalDto)
                .toList();
    }

    public List<RentalDto> getAllRentalsOverdue(String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN)) {
            logger.warn("You are not authorized!");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized!");
        }

        return rentalRepository.getRentals().stream()
                .filter(rental -> isBookOverdue(rental.getDateRented()))
                .map(rentalMapper::getRentalDto)
                .toList();
    }
}
