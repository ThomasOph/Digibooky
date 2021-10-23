package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookRepositoryTest {
    
    @Test
    void addNewBook() {
        Book book1 = new Book("isbn-1", "title 1", new Author("John", "Doe"), "summary 1");
        Book book2 = new Book("isbn-2", "title 2", new Author("John", "Doe"), "summary 2");
        Book book3 = new Book("isbn-3", "title 3", new Author("John", "Doe"), "summary 3");

        BookRepository repository = new BookRepository();

        repository.addBook(book1);
        repository.addBook(book2);
        repository.addBook(book3);

        Assertions.assertNotNull(repository.getBookRepository().values());
    }
}