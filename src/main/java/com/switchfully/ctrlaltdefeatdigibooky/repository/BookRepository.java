package com.switchfully.ctrlaltdefeatdigibooky.repository;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookRepository {
    private final Map<String, Book> bookMap;

    public BookRepository() {
        this.bookMap = new HashMap<>();
        addBook(new Book("978 0 596 52068 7", "The joys of Java", new Author(
                "John", "Doe"), "Summary 1"));
        addBook(new Book("978 0 181 16541 7", "Java Is Fun", new Author("Megan", "Fox"), "Summary 2"));
        addBook(new Book("979 0 856 45165 9", "Let's have some fun", new Author("Jane", "Doe"), "Summary 3"));
        addBook(new Book("979 1 806 46165 3", "Nothing is the start point", new Author("Logo", "Kim"), "Summary 4"));
        addBook(new Book("979 1 806 46165 8", "Design Pattern", new Author("Mark", "Sim"), "Summary 5"));
        addBook(new Book("979 1 806 46165 2", "Refactor", new Author("Sam", "Tim"), "Summary 6"));
        addBook(new Book("979 1 806 46165 1", "Clean Code", new Author("Bart", "Jim"), "Summary 7"));
    }

    public Map<String, Book> getBookRepository() {
        return bookMap;
    }

    public void addBook(Book book) {
        bookMap.put(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        Book bookToDelete = bookMap.values().stream()
                .filter(book -> BookService.onlyRetainNumbers(book.getIsbn()).equals(BookService.onlyRetainNumbers(isbn)))
                .findFirst().orElse(null);

        if (bookToDelete != null) {
            bookMap.get(bookToDelete.getIsbn()).setActive(false);
            bookMap.get(bookToDelete.getIsbn()).setCopiesOfBook(0);
        }
    }

    public void updateBook(Book updatedBook, String isbn) {
        Book bookToUpdate = bookMap.values().stream()
                .filter(book -> BookService.onlyRetainNumbers(book.getIsbn()).equals(BookService.onlyRetainNumbers(isbn)))
                .findFirst().orElse(null);

        if (bookToUpdate != null) {
            bookMap.get(bookToUpdate.getIsbn()).setTitle(updatedBook.getTitle());
            bookMap.get(bookToUpdate.getIsbn()).setAuthor(updatedBook.getAuthor());
            bookMap.get(bookToUpdate.getIsbn()).setSummary(updatedBook.getSummary());
            bookMap.get(bookToUpdate.getIsbn()).setCopiesOfBook(updatedBook.getCopiesOfBook());
            bookMap.get(bookToUpdate.getIsbn()).setActive(updatedBook.isActive());
        }
    }
}
