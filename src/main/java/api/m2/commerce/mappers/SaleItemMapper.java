package api.m2.commerce.mappers;

import api.m2.commerce.entities.SaleItem;
import api.m2.commerce.records.sales.SaleItemRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "margin", expression = "java(item.getMargin())")
    SaleItemRecord toRecord(SaleItem item);
}
