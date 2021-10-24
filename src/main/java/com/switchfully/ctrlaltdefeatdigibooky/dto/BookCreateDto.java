package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;

public class BookCreateDto {
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private final Integer copies;
    private final Boolean active;

    public BookCreateDto(String isbn, String title, Author author, String summary, Integer copies, Boolean active) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copies = copies;
        this.active = active;
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

    public Integer getCopies() {
        return copies;
    }

    public Boolean getActive() {
        return active;
    }
}
