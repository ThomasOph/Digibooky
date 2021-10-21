package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Author;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
    
    @Test
    void addNewBook() {
        Book book1 = new Book("isbn-1", "title 1", new Author("Christoph", "Parrez"), "summary 1");
        Book book2 = new Book("isbn-2", "title 2", new Author("Christoph", "Parrez"), "summary 2");
        Book book3 = new Book("isbn-3", "title 3", new Author("Christoph", "Parrez"), "summary 3");
        
        BookRepository repository = new BookRepository();
        
        repository.addBook(book1);
        repository.addBook(book2);
        repository.addBook(book3);

        Assertions.assertEquals(repository.getAllBooks(), List.of(book1, book2, book3));
    }
}