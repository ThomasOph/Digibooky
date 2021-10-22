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
        // TODO: DELETE DUMMY DATA
        Book book1 = new Book("ABCDEF", "The joys of Java", new Author("John", "Doe"), "Summary 1");
        Book book2 = new Book("D013FA", "Java Is Fun", new Author("Megan", "Fox"), "Summary 2");
        Book book3 = new Book("PQW1AD", "Let's have some fun", new Author("Jane", "Doe"), "Summary 3");
        bookMap.put(book1.getIsbn(), book1);
        bookMap.put(book2.getIsbn(), book2);
        bookMap.put(book3.getIsbn(), book3);
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

    public boolean hasISBN(String isbn) {
        return bookMap.containsKey(isbn);
    }

    public void deleteBook(String isbn) {
        bookMap.remove(isbn);
    }
}
