package api.m2.commerce.records.reports;

import api.m2.commerce.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public record DailySummaryRecord(
        LocalDate date,
        Long workspaceId,
        BigDecimal totalSales,
        BigDecimal totalCost,
        BigDecimal totalMargin,
        Integer salesCount,
        Integer itemsSold,
        Map<PaymentMethod, BigDecimal> salesByPaymentMethod
) {
}
