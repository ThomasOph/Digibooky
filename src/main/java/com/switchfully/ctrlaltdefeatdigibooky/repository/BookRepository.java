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
        addBook(new Book("9780596520687", "The joys of Java", new Author("John", "Doe"), "Summary 1"));
        addBook(new Book("9780181165417", "Java Is Fun", new Author("Megan", "Fox"), "Summary 2"));
        addBook(new Book("9790856451659", "Let's have some fun", new Author("Jane", "Doe"), "Summary 3"));
        addBook(new Book("9791806461653", "Nothing is the start point", new Author("Logo", "Kim"), "Summary 4"));
        addBook(new Book("9791806461658", "Design Pattern", new Author("Mark", "Sim"), "Summary 5"));
        addBook(new Book("9791806461652", "Refactor", new Author("Sam", "Tim"), "Summary 6"));
        addBook(new Book("9791806461651", "Clean Code", new Author("Bart", "Jim"), "Summary 7"));
    }

    // CODEREVIEW exposing your inner collection is never a good idea
    // you have to at the very least wrap it in an immutableCollection
    // same remark for all repositories
    public Map<String, Book> getBookRepository() {
        return bookMap;
    }

    public void addBook(Book book) {
        bookMap.put(book.getIsbn(), book);
    }

    public void deleteBook(Book bookToDelete) {
        bookMap.get(bookToDelete.getIsbn()).setActive(false);
        bookMap.get(bookToDelete.getIsbn()).setCopiesOfBook(0);
    }

    // CODEREVIEW a bit superfluous to have both arguments
    // see comment in BookService.updateBook too
    public void updateBook(Book updatedBook, Book bookToUpdate) {
        bookMap.get(bookToUpdate.getIsbn()).setTitle(updatedBook.getTitle());
        bookMap.get(bookToUpdate.getIsbn()).setAuthor(updatedBook.getAuthor());
        bookMap.get(bookToUpdate.getIsbn()).setSummary(updatedBook.getSummary());
        bookMap.get(bookToUpdate.getIsbn()).setCopiesOfBook(updatedBook.getCopiesOfBook());
        bookMap.get(bookToUpdate.getIsbn()).setActive(updatedBook.isActive());
    }
}
