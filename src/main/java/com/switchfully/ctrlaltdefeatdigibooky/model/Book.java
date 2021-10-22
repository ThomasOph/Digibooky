package com.switchfully.ctrlaltdefeatdigibooky.model;

public class Book {
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private boolean isActive;

    public Book(String isbn, String title, Author author, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isActive = true;
    }

    public Book(String isbn, String title, Author author) {
        this(isbn, title, author, "");
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
