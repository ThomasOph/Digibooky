package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;

public class BookDetailDto {
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private final Integer copiesOfBook;

    public BookDetailDto(String isbn, String title, Author author, String summary, Integer copies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copiesOfBook = copies;
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
}
