package com.switchfully.ctrlaltdefeatdigibooky.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Author {
    private final String firstName;
    private final String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public String getFullName() { return firstName + " " + lastName; }
}
