package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.FineType;

public record FineDto(FineType fineType, String userId, String amount) {}