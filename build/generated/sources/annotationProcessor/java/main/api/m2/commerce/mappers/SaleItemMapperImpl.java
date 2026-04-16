package api.m2.commerce.mappers;

import api.m2.commerce.entities.Product;
import api.m2.commerce.entities.SaleItem;
import api.m2.commerce.records.sales.SaleItemRecord;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class SaleItemMapperImpl implements SaleItemMapper {

    @Override
    public SaleItemRecord toRecord(SaleItem item) {
        if ( item == null ) {
            return null;
        }

        Long productId = null;
        String productName = null;
        Long id = null;
        Integer quantity = null;
        BigDecimal unitPrice = null;
        BigDecimal unitCost = null;
        BigDecimal discount = null;
        BigDecimal subtotal = null;

        productId = itemProductId( item );
        productName = itemProductName( item );
        id = item.getId();
        quantity = item.getQuantity();
        unitPrice = item.getUnitPrice();
        unitCost = item.getUnitCost();
        discount = item.getDiscount();
        subtotal = item.getSubtotal();

        BigDecimal margin = item.getMargin();

        SaleItemRecord saleItemRecord = new SaleItemRecord( id, productId, productName, quantity, unitPrice, unitCost, discount, subtotal, margin );

        return saleItemRecord;
    }

    private Long itemProductId(SaleItem saleItem) {
        Product product = saleItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String itemProductName(SaleItem saleItem) {
        Product product = saleItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
