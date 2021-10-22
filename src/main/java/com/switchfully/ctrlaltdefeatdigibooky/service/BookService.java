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
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookCreateDto newBookDto) {
        bookRepository.addBook(BookMapper.toBook(newBookDto));
    }

    public List<BookDto> getAllBooks() {
        return BookMapper.toDto(bookRepository.getBookRepository().values().stream().filter(Book::isActive).collect(Collectors.toList()));
    }

    public BookDetailDto getBookDetails(String isbn) {
        Book book = bookRepository.getBookRepository().get(isbn);
        if (book == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        if (!book.isActive())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " was deleted.");
        return BookMapper.toDetailDto(book);
    }

    public List<BookDto> findByISBN(String isbn) {
        return bookRepository.getBookRepository().values().stream()
                .filter(book -> book.getIsbn().matches(searchByWildCardsWithStar(isbn)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findByTitle(String title) {
        String titleToBeFound = title.toLowerCase(Locale.ROOT);

        return bookRepository.getBookRepository().values().stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).matches(searchByWildCardsWithStar(titleToBeFound)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findByAuthor(String author) {
        String authorToBeFound = author.toLowerCase(Locale.ROOT);

        return bookRepository.getBookRepository().values().stream()
                .filter(book ->
                        book.getAuthor().getFirstName().toLowerCase(Locale.ROOT).matches(searchByWildCardsWithStar(authorToBeFound)) ||
                        book.getAuthor().getLastName().toLowerCase(Locale.ROOT).matches(searchByWildCardsWithStar(authorToBeFound))
                )
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    private String searchByWildCardsWithStar(String input) {
        if (!input.startsWith("*")) input = "*" + input;
        if (!input.endsWith("*")) input += "*";
        input = input.replace("*", "(.*)");
        return input;
    }

    public void deleteBook(String isbn) {
        if (!bookRepository.getBookRepository().containsKey(isbn))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book with ISBN " + isbn + " doesn't exist.");
        bookRepository.deleteBook(isbn);
    }
}
