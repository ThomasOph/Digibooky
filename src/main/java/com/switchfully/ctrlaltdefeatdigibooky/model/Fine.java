package com.switchfully.ctrlaltdefeatdigibooky.model;

// CODEREVIEW why only one record and the rest classes ?
public record Fine(FineType fineType, String userId, double amount) {}