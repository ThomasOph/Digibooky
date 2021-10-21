package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;

import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Author;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookServiceTest {

    @Test
    void addNewBookDto() {
        Book book1 = new Book("isbn-1", "title 1", new Author("Christoph", "Parrez"), "summary 1");
        Book book2 = new Book("isbn-2", "title 2", new Author("Christoph", "Parrez"), "summary 2");
        Book book3 = new Book("isbn-3", "title 3", new Author("Christoph", "Parrez"), "summary 3");

        BookRepository repository = new BookRepository();
        BookMapper mapper = new BookMapper();
        BookService service = new BookService(repository, mapper);

        BookCreateDto bookDto1 = mapper.toCreateDto(book1);
        BookCreateDto bookDto2 = mapper.toCreateDto(book2);
        BookCreateDto bookDto3 = mapper.toCreateDto(book3);

        service.addBook(bookDto1);
        service.addBook(bookDto2);
        service.addBook(bookDto3);

        Assertions.assertNotNull(service.getAllBooks());
    }
}