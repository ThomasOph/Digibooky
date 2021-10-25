package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookUpdateDto;
import com.switchfully.ctrlaltdefeatdigibooky.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAll(@RequestParam(required = false) String isbn,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) String author) {
        if (isbn != null) return bookService.getBooksByISBN(isbn);
        if (title != null) return bookService.getBooksByTitle(title);
        if (author != null) return bookService.getBooksByAuthor(author);

        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{isbn}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailDto getById(@PathVariable String isbn) {
        return bookService.getBookDetails(isbn);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody BookCreateDto bookDto,
                    @RequestHeader(value = "uuid", required = false) String uuid) {
        bookService.addBook(bookDto, uuid);
    }

    @DeleteMapping(path = "/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("isbn") String isbn,
                       @RequestHeader(value = "uuid", required = false) String uuid) {
        bookService.deleteBook(isbn, uuid);
    }

    @PutMapping(consumes = "application/json", path = "/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBookWithIsbn(@RequestBody BookUpdateDto bookDto,
                                   @PathVariable("isbn") String isbn,
                                   @RequestHeader(value = "uuid", required = false) String uuid) {
        bookService.updateBook(bookDto, isbn, uuid);
    }
}
