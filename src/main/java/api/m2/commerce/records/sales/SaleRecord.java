package api.m2.commerce.records.sales;

import api.m2.commerce.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record SaleRecord(
        Long id,
        LocalDate date,
        BigDecimal total,
        BigDecimal totalCost,
        BigDecimal margin,
        Long currencyId,
        PaymentMethod paymentMethod,
        String notes,
        Long workspaceId,
        List<SaleItemRecord> items,
        Instant createdAt
) {
}
