package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRepository {
    private final List<Book> bookList;

    public BookRepository() {
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getAllBooks() {
        return bookList;
    }
}
