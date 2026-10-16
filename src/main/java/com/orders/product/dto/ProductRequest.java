package com.orders.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * stock y active son opcionales: si no se envían quedan en 0 y true.
 */
public record ProductRequest(

        @NotNull(message = "La categoría es obligatoria")
        Long categoryId,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 160, message = "El nombre no puede superar los 160 caracteres")
        String name,

        String description,

        @Size(max = 100, message = "La marca no puede superar los 100 caracteres")
        String brand,

        @NotBlank(message = "El SKU es obligatorio")
        @Size(max = 50, message = "El SKU no puede superar los 50 caracteres")
        String sku,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.00", message = "El precio no puede ser negativo")
        @Digits(integer = 10, fraction = 2, message = "El precio admite hasta 10 enteros y 2 decimales")
        BigDecimal price,

        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        LocalDate expirationDate,

        Boolean active
) {
}
