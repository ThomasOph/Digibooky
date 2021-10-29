package com.switchfully.ctrlaltdefeatdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// CODEREVIEW sometimes you use custom exceptions like these, sometimes you create ResponseStatusExceptions without extending the class
// see for instance addBook
// decide on one strategy and use it everywhere
public class BookNotFoundException extends ResponseStatusException {

    public BookNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "We no longer have this book");
    }
}
