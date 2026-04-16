package api.m2.commerce.records.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductToAdd(
        @NotBlank(message = "El nombre es requerido")
        String name,
        String sku,
        String description,
        @NotNull(message = "El costo es requerido")
        @Positive(message = "El costo debe ser mayor a cero")
        BigDecimal cost,
        @NotNull(message = "El precio es requerido")
        @Positive(message = "El precio debe ser mayor a cero")
        BigDecimal price,
        @NotNull(message = "La moneda es requerida")
        Long currencyId,
        @NotNull(message = "El workspace es requerido")
        Long workspaceId,
        Long categoryId,
        Integer initialStock
) {
}
