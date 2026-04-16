package api.m2.commerce.records.stock;

import jakarta.validation.constraints.NotNull;

public record StockAdjustment(
        @NotNull(message = "La cantidad es requerida")
        Integer quantity,
        String reason
) {
}
