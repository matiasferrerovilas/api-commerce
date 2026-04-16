package api.m2.commerce.mappers;

import api.m2.commerce.entities.Product;
import api.m2.commerce.records.products.ProductRecord;
import api.m2.commerce.records.products.ProductToAdd;
import api.m2.commerce.records.products.ProductToUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, StockMapper.class})
public interface ProductMapper {

    ProductRecord toRecord(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Product toEntity(ProductToAdd dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workspaceId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ProductToUpdate dto, @MappingTarget Product entity);
}
