package api.m2.commerce.records.products;

import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.stock.StockRecord;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductRecord(
        Long id,
        String name,
        String sku,
        String description,
        BigDecimal cost,
        BigDecimal price,
        Long currencyId,
        Boolean isActive,
        Long workspaceId,
        CategoryRecord category,
        StockRecord stock,
        Instant createdAt,
        Instant updatedAt
) {
}
