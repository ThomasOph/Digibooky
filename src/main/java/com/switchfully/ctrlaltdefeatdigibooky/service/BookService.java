package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
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
    private final UserUtils userService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       UserService userService ) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public void addBook(BookCreateDto newBookDto) {
        // TODO:: Only librarian should be able to add a book
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

    public void deleteBook(String isbn) {
        // TODO:: Only librarian should be able to delete a book
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


    public BookDto updateBookInfo(Book book, String id) {
        return null;
    }
}
