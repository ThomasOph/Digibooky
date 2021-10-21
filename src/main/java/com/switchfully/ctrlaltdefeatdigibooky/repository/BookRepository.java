package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookRepository {
    private final Map<String, Book> bookMap;

    public BookRepository() {
        this.bookMap = new HashMap<>();
    }

    public void addBook(Book book) {
        bookMap.put(book.getIsbn(), book);
    }

    public List<Book> getAllBooks() {
        return bookMap.values().stream().toList();
    }

    public Book getByISBN(String isbn) {
        return bookMap.get(isbn);
    }
}
