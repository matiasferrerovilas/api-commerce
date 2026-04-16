package api.m2.commerce.services.sale;

import api.m2.commerce.entities.Product;
import api.m2.commerce.entities.Sale;
import api.m2.commerce.entities.SaleItem;
import api.m2.commerce.entities.User;
import api.m2.commerce.mappers.SaleMapper;
import api.m2.commerce.records.sales.SaleItemToAdd;
import api.m2.commerce.records.sales.SaleRecord;
import api.m2.commerce.records.sales.SaleToAdd;
import api.m2.commerce.repositories.SaleRepository;
import api.m2.commerce.services.product.ProductQueryService;
import api.m2.commerce.services.stock.StockAddService;
import api.m2.commerce.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleAddService {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private static final int DISCOUNT_SCALE = 4;
    private static final int MONEY_SCALE = 2;

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductQueryService productQueryService;
    private final StockAddService stockAddService;
    private final UserService userService;

    public SaleRecord create(SaleToAdd dto) {
        User currentUser = userService.getOrCreateUser();

        Sale sale = Sale.builder()
                .date(dto.date() != null ? dto.date() : LocalDate.now())
                .currencyId(dto.currencyId())
                .paymentMethod(dto.paymentMethod())
                .notes(dto.notes())
                .workspaceId(dto.workspaceId())
                .user(currentUser)
                .total(BigDecimal.ZERO)
                .totalCost(BigDecimal.ZERO)
                .build();

        BigDecimal totalSale = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;

        for (SaleItemToAdd itemDto : dto.items()) {
            SaleItem item = this.createSaleItem(itemDto);
            sale.addItem(item);

            totalSale = totalSale.add(item.getSubtotal());
            totalCost = totalCost.add(item.getUnitCost().multiply(BigDecimal.valueOf(item.getQuantity())));

            stockAddService.decrementStock(itemDto.productId(), itemDto.quantity());
        }

        sale.setTotal(totalSale);
        sale.setTotalCost(totalCost);

        Sale saved = saleRepository.save(sale);
        return saleMapper.toRecord(saved);
    }

    public void delete(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        saleRepository.delete(sale);
    }

    private SaleItem createSaleItem(SaleItemToAdd dto) {
        Product product = productQueryService.getById(dto.productId());

        BigDecimal unitPrice = dto.unitPrice() != null ? dto.unitPrice() : product.getPrice();
        BigDecimal discount = dto.discount() != null ? dto.discount() : BigDecimal.ZERO;

        BigDecimal subtotal = this.calculateSubtotal(unitPrice, dto.quantity(), discount);

        return SaleItem.builder()
                .product(product)
                .quantity(dto.quantity())
                .unitPrice(unitPrice)
                .unitCost(product.getCost())
                .discount(discount)
                .subtotal(subtotal)
                .build();
    }

    private BigDecimal calculateSubtotal(BigDecimal unitPrice, Integer quantity, BigDecimal discountPercent) {
        BigDecimal grossTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));

        if (discountPercent.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discountMultiplier = BigDecimal.ONE.subtract(
                    discountPercent.divide(ONE_HUNDRED, DISCOUNT_SCALE, RoundingMode.HALF_UP)
            );
            return grossTotal.multiply(discountMultiplier).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }

        return grossTotal.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
