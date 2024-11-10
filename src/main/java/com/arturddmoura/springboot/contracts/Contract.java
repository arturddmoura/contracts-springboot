package com.arturddmoura.springboot.contracts;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.UUID;

public record Contract(UUID id,
                       UUID externalId,
                       Statuses status,
                       String key,
                       Areas area,
                       Products product,
                       LocalDateTime contractDate,
                       String createdBy
) {
    public Contract {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (status == null) {
            status = Statuses.pending;
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
        if (createdBy == null) {
            throw new IllegalArgumentException("Created by cannot be null");
        }

        if (!EnumSet.allOf(Products.class).contains(product)) {
            throw new IllegalArgumentException("Invalid product: " + product);
        }
        if (contractDate == null) {
            contractDate = LocalDateTime.now();
        }
    }
}