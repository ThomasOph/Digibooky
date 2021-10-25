package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;

import java.util.Objects;

public class BookDto {
    private final String isbn;
    private final String title;
    private final Author author;


    public BookDto(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(isbn, bookDto.isbn) && Objects.equals(title, bookDto.title) && Objects.equals(author, bookDto.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author);
    }
}
