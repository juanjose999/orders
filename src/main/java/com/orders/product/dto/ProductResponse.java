package com.orders.product.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        Long categoryId,
        String name,
        String description,
        String brand,
        String sku,
        BigDecimal price,
        Integer stock,
        LocalDate expirationDate,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
