package com.switchfully.ctrlaltdefeatdigibooky.controller;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDetailDto;
import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public List<BookDto> getAll(@RequestParam(required = false) String isbn, @RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        if (isbn != null) return bookService.findByISBN(isbn);
        if (title != null) return bookService.findByTitle(title);
        if (author != null) return bookService.findByAuthor(author);

        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailDto getById(@PathVariable String isbn) {
        return bookService.getBookDetails(isbn);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody BookCreateDto bookDto) {
        bookService.addBook(bookDto);
    }

    @DeleteMapping(path = "/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("isbn") String isbn) {
        bookService.deleteBook(isbn);
    }

    @ExceptionHandler
    protected void exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
