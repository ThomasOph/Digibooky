package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookRepository {
    private final Map<String, Book> bookMap;

    public BookRepository() {
        this.bookMap = new HashMap<>();
        addBook(new Book("ABCDEF", "The joys of Java", new Author("John", "Doe"), "Summary 1"));
        addBook(new Book("D013FA", "Java Is Fun", new Author("Megan", "Fox"), "Summary 2"));
        addBook(new Book("PQW1AD", "Let's have some fun", new Author("Jane", "Doe"), "Summary 3"));
    }

    public Map<String, Book> getBookRepository() {
        return bookMap;
    }

    public void addBook(Book book) {
        bookMap.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        bookMap.get(isbn).setActive(false);
    }

    public void updateBook(Book updatedBook, String isbn) {
        bookMap.get(isbn).setTitle(updatedBook.getTitle());
        bookMap.get(isbn).setAuthor(updatedBook.getAuthor());
        bookMap.get(isbn).setSummary(updatedBook.getSummary());
    }
}
