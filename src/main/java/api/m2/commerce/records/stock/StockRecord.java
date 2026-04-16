package api.m2.commerce.records.stock;

import java.time.Instant;

public record StockRecord(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        Integer minStock,
        Boolean isLowStock,
        Instant updatedAt
) {
}
