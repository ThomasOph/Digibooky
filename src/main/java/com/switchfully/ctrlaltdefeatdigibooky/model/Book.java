package com.switchfully.ctrlaltdefeatdigibooky.model;

public class Book {
    private String isbn;
    private String title;
    private Author author;
    private String summary;
    private int copiesOfBook;
    private boolean isActive;

    public Book(String isbn, String title, Author author, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copiesOfBook = 1;
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

    public int getCopiesOfBook() {
        return copiesOfBook;
    }

    public boolean isActive() {
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
