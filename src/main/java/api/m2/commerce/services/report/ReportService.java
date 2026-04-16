package api.m2.commerce.services.report;

import api.m2.commerce.entities.Sale;
import api.m2.commerce.enums.PaymentMethod;
import api.m2.commerce.records.reports.DailySummaryRecord;
import api.m2.commerce.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final SaleRepository saleRepository;

    public DailySummaryRecord getDailySummary(Long workspaceId, LocalDate date) {
        List<Sale> sales = saleRepository.findDailySales(workspaceId, date);

        if (sales.isEmpty()) {
            return new DailySummaryRecord(
                    date,
                    workspaceId,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    0,
                    0,
                    Map.of()
            );
        }

        BigDecimal totalSales = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;
        int itemsSold = 0;
        Map<PaymentMethod, BigDecimal> salesByPaymentMethod = new EnumMap<>(PaymentMethod.class);

        for (Sale sale : sales) {
            totalSales = totalSales.add(sale.getTotal());
            totalCost = totalCost.add(sale.getTotalCost());

            itemsSold += sale.getItems().stream()
                    .mapToInt(item -> item.getQuantity())
                    .sum();

            salesByPaymentMethod.merge(
                    sale.getPaymentMethod(),
                    sale.getTotal(),
                    BigDecimal::add
            );
        }

        return new DailySummaryRecord(
                date,
                workspaceId,
                totalSales,
                totalCost,
                totalSales.subtract(totalCost),
                sales.size(),
                itemsSold,
                salesByPaymentMethod
        );
    }

    public DailySummaryRecord getTodaySummary(Long workspaceId) {
        return this.getDailySummary(workspaceId, LocalDate.now());
    }
}
