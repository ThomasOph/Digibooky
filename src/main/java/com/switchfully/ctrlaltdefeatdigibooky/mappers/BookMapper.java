package com.switchfully.ctrlaltdefeatdigibooky.mappers;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public List<BookDto> toDto(List<Book> bookList) {
        return bookList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public Book toBook(BookDto dto) {
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getSummary());
    }
}
