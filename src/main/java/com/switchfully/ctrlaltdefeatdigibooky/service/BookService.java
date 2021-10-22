package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
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

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookCreateDto newBookDto) {
        bookRepository.addBook(BookMapper.toBook(newBookDto));
    }

    public List<BookDto> getAllBooks() {
        return BookMapper.toDto(bookRepository.getAllBooks());
    }

    public BookDetailDto getBookDetails(String isbn) {
        if (!bookRepository.hasISBN(isbn)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ISBN " + isbn + " doesn't exist.");
        return BookMapper.toDetailDto(bookRepository.getByISBN(isbn));
    }

    public List<BookDto> findByISBN(String isbn) {
        return bookRepository.getAllBooks().stream()
                .filter(book -> book.getIsbn().matches(searchByWildCardsWithStar(isbn)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findByTitle(String title) {
        return bookRepository.getAllBooks().stream()
                .filter(book -> book.getTitle().matches(searchByWildCardsWithStar(title)))
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    private String searchByWildCardsWithStar(String input) {
        if (!input.startsWith("*")) input = "*" + input;
        if (!input.endsWith("*")) input += "*";
        return input.replace("*", "(.*)");
    }
}
