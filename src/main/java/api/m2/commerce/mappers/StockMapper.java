package api.m2.commerce.mappers;

import api.m2.commerce.entities.Stock;
import api.m2.commerce.records.stock.StockRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "isLowStock", expression = "java(stock.getQuantity() <= stock.getMinStock())")
    StockRecord toRecord(Stock stock);
}
