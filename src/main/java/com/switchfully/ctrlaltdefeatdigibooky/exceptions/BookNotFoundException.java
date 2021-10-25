package com.switchfully.ctrlaltdefeatdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFoundException extends ResponseStatusException {

    public BookNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "We no longer have this book");
    }
}
