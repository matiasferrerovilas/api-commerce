package api.m2.commerce.mappers;

import api.m2.commerce.entities.Sale;
import api.m2.commerce.records.sales.SaleRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SaleItemMapper.class})
public interface SaleMapper {

    @Mapping(target = "margin", expression = "java(sale.getMargin())")
    SaleRecord toRecord(Sale sale);
}
