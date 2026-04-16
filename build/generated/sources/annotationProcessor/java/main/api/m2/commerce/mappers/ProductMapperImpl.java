package api.m2.commerce.mappers;

import api.m2.commerce.entities.Product;
import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.products.ProductRecord;
import api.m2.commerce.records.products.ProductToAdd;
import api.m2.commerce.records.products.ProductToUpdate;
import api.m2.commerce.records.stock.StockRecord;
import java.math.BigDecimal;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StockMapper stockMapper;

    @Override
    public ProductRecord toRecord(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String sku = null;
        String description = null;
        BigDecimal cost = null;
        BigDecimal price = null;
        Long currencyId = null;
        Boolean isActive = null;
        Long workspaceId = null;
        CategoryRecord category = null;
        StockRecord stock = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = product.getId();
        name = product.getName();
        sku = product.getSku();
        description = product.getDescription();
        cost = product.getCost();
        price = product.getPrice();
        currencyId = product.getCurrencyId();
        isActive = product.getIsActive();
        workspaceId = product.getWorkspaceId();
        category = categoryMapper.toRecord( product.getCategory() );
        stock = stockMapper.toRecord( product.getStock() );
        createdAt = product.getCreatedAt();
        updatedAt = product.getUpdatedAt();

        ProductRecord productRecord = new ProductRecord( id, name, sku, description, cost, price, currencyId, isActive, workspaceId, category, stock, createdAt, updatedAt );

        return productRecord;
    }

    @Override
    public Product toEntity(ProductToAdd dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( dto.name() );
        product.sku( dto.sku() );
        product.description( dto.description() );
        product.cost( dto.cost() );
        product.price( dto.price() );
        product.currencyId( dto.currencyId() );
        product.workspaceId( dto.workspaceId() );

        product.isActive( true );

        return product.build();
    }

    @Override
    public void updateEntity(ProductToUpdate dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.name() != null ) {
            entity.setName( dto.name() );
        }
        if ( dto.sku() != null ) {
            entity.setSku( dto.sku() );
        }
        if ( dto.description() != null ) {
            entity.setDescription( dto.description() );
        }
        if ( dto.cost() != null ) {
            entity.setCost( dto.cost() );
        }
        if ( dto.price() != null ) {
            entity.setPrice( dto.price() );
        }
        if ( dto.currencyId() != null ) {
            entity.setCurrencyId( dto.currencyId() );
        }
        if ( dto.isActive() != null ) {
            entity.setIsActive( dto.isActive() );
        }
    }
}
