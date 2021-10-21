package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public List<BookDto> toDto(List<Book> bookList) {
        return bookList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor());
    }

    public BookDetailDto toDetailDto(Book book) {
        return new BookDetailDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public BookCreateDto toCreateDto(Book book) {
        return new BookCreateDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public Book toBook(BookDto dto) {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor());
    }

    public Book toBook(BookDetailDto dto) {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getSummary());
    }

    public Book toBook(BookCreateDto dto) {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getSummary());
    }
}
