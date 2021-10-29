package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;

public class BookUpdateDto {
    private final String title;
    // CODEREVIEW you should be creating an AuthorDto class to use here
    private final Author author;
    private final String summary;
    private final Integer copies;
    private final Boolean active;

    public BookUpdateDto(String title, Author author, String summary, Integer copies, Boolean active) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copies = copies;
        this.active = active;
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
