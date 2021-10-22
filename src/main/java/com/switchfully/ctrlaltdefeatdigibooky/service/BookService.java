package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.model.UserRole;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public void addBook(BookCreateDto newBookDto, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to add books.");

        bookRepository.addBook(BookMapper.toBook(newBookDto));
    }

    public List<BookDto> getAllBooks() {
        return BookMapper.toDto(bookRepository.getBookRepository().values().stream().filter(Book::isActive).collect(Collectors.toList()));
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
                .filter(book ->
                        book.getAuthor().getFirstName().matches(searchByWildCardsWithStar(author)) ||
                                book.getAuthor().getLastName().matches(searchByWildCardsWithStar(author)) ||
                                book.getAuthor().getFullName().matches(searchByWildCardsWithStar(author))
                )
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDetailDto getBookDetails(String isbn) {
        Book book = bookRepository.getBookRepository().get(isbn);
        if (book == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        if (!book.isActive())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " was deleted.");
        return BookMapper.toDetailDto(book);
    }

    public void deleteBook(String isbn, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete books.");

        Book book = bookRepository.getBookRepository().get(isbn);
        if (book == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        if (!book.isActive())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " was already deleted.");
        bookRepository.deleteBook(isbn);
    }

    private String searchByWildCardsWithStar(String input) {
        return "(?i).*" + input.replace("*", "(.*)") + ".*";
    }

    public void updateBook(BookCreateDto bookDtoUpdated, String isbn, String uuid) {
        if (!userService.isUUIDUserRole(uuid, UserRole.LIBRARIAN))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update books.");

        if (!bookRepository.getBookRepository().containsKey(isbn))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");

        bookRepository.updateBook(BookMapper.toBook(bookDtoUpdated), isbn);
    }
}
