package com.switchfully.ctrlaltdefeatdigibooky.model;

public enum FineType {
    OVERDUE("overdue"), DAMAGE("damage");
    private final String toString;

    FineType(String toString) {
        this.toString = toString;
    }
}
