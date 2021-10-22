package com.switchfully.ctrlaltdefeatdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

	public UserNotFoundException() {
		super(HttpStatus.UNAUTHORIZED, "User does not exist");
	}
}
