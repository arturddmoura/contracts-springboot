package com.arturddmoura.springboot.contracts;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.UUID;

public record Contract(UUID id, String key, Areas area, Products product, LocalDateTime contractDate) {
    public Contract {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (area == null) {
            throw new IllegalArgumentException("Area cannot be null");
        }
        if (!EnumSet.allOf(Areas.class).contains(area)) {
            throw new IllegalArgumentException("Invalid area: " + area);
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!EnumSet.allOf(Products.class).contains(product)) {
            throw new IllegalArgumentException("Invalid product: " + product);
        }
        if (contractDate == null) {
            contractDate = LocalDateTime.now();
        }
    }
}