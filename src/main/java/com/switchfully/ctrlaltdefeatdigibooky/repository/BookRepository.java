package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookRepository {
    private final List<Book> repository;

    public BookRepository() {
        this.repository = new ArrayList<>();
    }

    public void addBook(Book book) {
        repository.add(book);
    }

    public List<Book> getAllBooks() {
        return repository;
    }
}
