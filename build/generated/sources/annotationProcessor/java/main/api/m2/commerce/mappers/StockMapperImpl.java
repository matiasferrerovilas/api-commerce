package api.m2.commerce.mappers;

import api.m2.commerce.entities.Product;
import api.m2.commerce.entities.Stock;
import api.m2.commerce.records.stock.StockRecord;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public StockRecord toRecord(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        Long productId = null;
        String productName = null;
        Long id = null;
        Integer quantity = null;
        Integer minStock = null;
        Instant updatedAt = null;

        productId = stockProductId( stock );
        productName = stockProductName( stock );
        id = stock.getId();
        quantity = stock.getQuantity();
        minStock = stock.getMinStock();
        updatedAt = stock.getUpdatedAt();

        Boolean isLowStock = stock.getQuantity() <= stock.getMinStock();

        StockRecord stockRecord = new StockRecord( id, productId, productName, quantity, minStock, isLowStock, updatedAt );

        return stockRecord;
    }

    private Long stockProductId(Stock stock) {
        Product product = stock.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String stockProductName(Stock stock) {
        Product product = stock.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
