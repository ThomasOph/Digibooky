package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.DigibookyApplication;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    private static final String ISBN_13_REGEX_PATTERN = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";
    private final BookRepository bookRepository;
    private final UserService userService;
    private final RentalService rentalService;

    public BookService(BookRepository bookRepository,
                       UserService userService,
                       @Lazy RentalService rentalService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.rentalService = rentalService;
    }

    public List<BookDto> getAllBooks() {
        return BookMapper.toDto(bookRepository.getBookRepository().values().
                stream().
                filter(Book::isActive)
                .collect(Collectors.toList()));
    }

    public Book getBookByISBN(String isbn) {
        return bookRepository.getBookRepository().get(isbn);
    }

    public List<BookDto> getBooksByISBN(String isbn) {
        return bookRepository.getBookRepository().values().stream()
                .filter(Book::isActive)
                .filter(book -> book.getIsbn().matches(searchByWildCardsWithStar(isbn)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByTitle(String title) {
        return bookRepository.getBookRepository().values().stream()
                .filter(Book::isActive)
                .filter(book -> book.getTitle().matches(searchByWildCardsWithStar(title)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByAuthor(String author) {
        return bookRepository.getBookRepository().values().stream()
                .filter(Book::isActive)
                .filter(book -> book.getAuthor().getFirstName().matches(searchByWildCardsWithStar(author)) ||
                        book.getAuthor().getLastName().matches(searchByWildCardsWithStar(author)) ||
                        book.getAuthor().getFullName().matches(searchByWildCardsWithStar(author))
                )
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDetailDto getBookDetails(String isbn) {
        Book book = bookRepository.getBookRepository().get(isbn);
        if (book == null) {
            logger.warn("The book with ISBN " + isbn + " doesn't exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        }
        if (!book.isActive()) {
            logger.warn("The book with ISBN " + isbn + " was deleted.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " was deleted.");
        }

        return BookMapper.toDetailDto(book,
                rentalService.getUsersRentingBook(isbn));
    }

    public void deleteBook(String isbn, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN)) {
            logger.warn("You are not authorized to delete books.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to delete books.");
        }

        Book book = bookRepository.getBookRepository().get(isbn);
        if (book == null) {
            logger.warn("The book with ISBN " + isbn + " doesn't exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        }
        if (!book.isActive()) {
            logger.warn("The book with ISBN " + isbn + " was already deleted.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " was already deleted.");
        }

        logger.info("Deleted book " + isbn);
        bookRepository.deleteBook(isbn);
    }

    public void addBook(BookCreateDto newBookDto, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN)) {
            logger.warn("You are not authorized to add books.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to add books.");
        }

        if (isMissingInput(newBookDto)) {
            logger.warn("The ISBN, title and author's last name are required.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ISBN, title and author's last name are required.");
        }

        if (!isValidISBN(newBookDto.getIsbn())) {
            logger.warn(newBookDto.getIsbn() + " is not a valid ISBN number.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, newBookDto.getIsbn() + " is not a valid ISBN number.");
        }

        if (newBookDto.getCopies() < 0) {
            logger.warn("The number of copies cannot be negative.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of copies cannot be negative.");
        }

        if (bookRepository.getBookRepository().containsKey(newBookDto.getIsbn())) {
            logger.warn("This book already exists. You should update the book.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This book already exists. You should update the book.");
        }

        logger.info("Added book " + newBookDto.getIsbn());
        bookRepository.addBook(BookMapper.toBook(newBookDto));
    }

    public void updateBook(BookCreateDto bookDtoUpdated, String isbn, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN)) {
            logger.warn("You are not authorized to update books.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update books.");
        }

        if (!bookRepository.getBookRepository().containsKey(isbn)) {
            logger.warn("The book with ISBN " + isbn + " doesn't exist.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        }

        logger.info("Updated book " + isbn);
        bookRepository.updateBook(BookMapper.toBook(bookDtoUpdated), isbn);
    }

    private String searchByWildCardsWithStar(String input) {
        return "(?i).*" + input.replace("*", "(.*)") + ".*";
    }

    private boolean isValidISBN(String isbn) {
        return isbn.matches(ISBN_13_REGEX_PATTERN);
    }

    private boolean isMissingInput(BookCreateDto bookDto) {
        return bookDto.getIsbn() == null ||
                bookDto.getIsbn().trim().isEmpty() ||
                bookDto.getTitle() == null ||
                bookDto.getTitle().trim().isEmpty() ||
                bookDto.getAuthor().getLastName() == null ||
                bookDto.getAuthor().getLastName().trim().isEmpty();
    }
}
