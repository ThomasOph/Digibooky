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
        addBook(new Book("978 0 596 52068 7", "The joys of Java", new Author("John", "Doe"), "Summary 1"));
        addBook(new Book("978 0 181 16541 7", "Java Is Fun", new Author("Megan", "Fox"), "Summary 2"));
        addBook(new Book("979 0 856 45165 9", "Let's have some fun", new Author("Jane", "Doe"), "Summary 3"));
    }

    public Map<String, Book> getBookRepository() {
        return bookMap;
    }

    public void addBook(Book book) {
        if (bookMap.containsKey(book.getIsbn()))
            book.setCopiesOfBook(bookMap.get(book.getIsbn()).getCopiesOfBook() + book.getCopiesOfBook());

        bookMap.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        bookMap.get(isbn).setActive(false);
        bookMap.get(isbn).setCopiesOfBook(0);
    }

    public void updateBook(Book updatedBook, String isbn) {
        bookMap.get(isbn).setTitle(updatedBook.getTitle());
        bookMap.get(isbn).setAuthor(updatedBook.getAuthor());
        bookMap.get(isbn).setSummary(updatedBook.getSummary());
        bookMap.get(isbn).setCopiesOfBook(updatedBook.getCopiesOfBook());
        bookMap.get(isbn).setActive(updatedBook.isActive());
    }
}
