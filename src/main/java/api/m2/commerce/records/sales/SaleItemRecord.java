package api.m2.commerce.records.sales;

import java.math.BigDecimal;

public record SaleItemRecord(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal unitCost,
        BigDecimal discount,
        BigDecimal subtotal,
        BigDecimal margin
) {
}
