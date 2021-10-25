package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.RentalMapper;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.UserMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.*;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.FineRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.RentalRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.UserRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@SpringBootTest
class BookServiceTest {

    private final BookService service;
    private final BookRepository repository;

    @Autowired
    public BookServiceTest(BookService service, BookRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Test
    void addNewBookDto() {
        Book book1 = new Book("979 1 596 52068 7", "title 1", new Author("John", "Doe"), "summary 1");
        Book book2 = new Book("979 2 596 52068 7", "title 2", new Author("John", "Doe"), "summary 2");
        Book book3 = new Book("979 3 596 52068 7", "title 3", new Author("John", "Doe"), "summary 3");

        BookCreateDto bookDto1 = BookMapper.toCreateDto(book1);
        BookCreateDto bookDto2 = BookMapper.toCreateDto(book2);
        BookCreateDto bookDto3 = BookMapper.toCreateDto(book3);

        repository.getBookRepository().clear();
        service.addBook(bookDto1, "TheLibrarian");
        service.addBook(bookDto2, "TheLibrarian");
        service.addBook(bookDto3, "TheLibrarian");

        Comparator<BookDto> comparator = Comparator.comparing(BookDto::getIsbn);

        List<BookDto> expected = BookMapper.toDto(List.of(book1, book2, book3));
        expected.sort(comparator);

        List<BookDto> actual = service.getAllBooks();
        actual.sort(comparator);

        Assertions.assertEquals(expected, actual);
    }
}