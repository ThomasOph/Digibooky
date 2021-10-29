package com.switchfully.ctrlaltdefeatdigibooky.dto;

import com.switchfully.ctrlaltdefeatdigibooky.model.FineType;

// CODEREVIEW a bit strange to have one record
public record FineDto(FineType fineType, String userId, String amount) {}