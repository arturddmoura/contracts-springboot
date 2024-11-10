package com.arturddmoura.springboot.contracts;

import java.util.UUID;

public record Contract(UUID id, String key, String area, String product) {
    public Contract {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (area == null || area.isBlank()) {
            throw new IllegalArgumentException("Area cannot be null or empty");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
    }
}