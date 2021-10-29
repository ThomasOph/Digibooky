package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.Author;

import java.util.List;

// CODEREVIEW why have 2 Dtos: BookDetailDto and BookDto?
public class BookDetailDto {
    private final String isbn;
    private final String title;
    // CODEREVIEW you should be creating an AuthorDto class to use here
    private final Author author;
    private final String summary;
    private final Integer copiesOfBook;
    private final List<String> usersRentingBook;

    public BookDetailDto(String isbn, String title, Author author, String summary, Integer copies, List<String> usersRentingBook) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.copiesOfBook = copies;
        this.usersRentingBook = usersRentingBook;
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

    public List<String> getUsersRentingBook() {
        return usersRentingBook;
    }
}
