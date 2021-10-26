package com.switchfully.ctrlaltdefeatdigibooky.model;

import com.switchfully.ctrlaltdefeatdigibooky.service.BookService;

import javax.validation.constraints.NotNull;

public class Book {
    private final String isbn;
    private String title;
    private Author author;
    private String summary;
    private int copiesOfBook;
    private Boolean isActive;

    public Book(String isbn, String title, Author author, String summary, Integer copies, Boolean isActive) {
        this.isbn = BookService.onlyRetainNumbers(isbn);
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copiesOfBook = copies == null ? 1 : copies;
        this.isActive = isActive == null || isActive;
    }

    public Book(String isbn, String title, Author author, String summary) {
        this(isbn, title, author, "", null, null);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public int getCopiesOfBook() {
        return copiesOfBook;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCopiesOfBook(int copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
