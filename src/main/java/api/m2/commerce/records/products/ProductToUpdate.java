package api.m2.commerce.records.products;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductToUpdate(
        String name,
        String sku,
        String description,
        @Positive(message = "El costo debe ser mayor a cero")
        BigDecimal cost,
        @Positive(message = "El precio debe ser mayor a cero")
        BigDecimal price,
        Long currencyId,
        Long categoryId,
        Boolean isActive
) {
}
