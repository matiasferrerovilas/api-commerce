package api.m2.commerce.records.sales;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record SaleItemToAdd(
        @NotNull(message = "El producto es requerido")
        Long productId,
        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad debe ser mayor a cero")
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal discount
) {
}
