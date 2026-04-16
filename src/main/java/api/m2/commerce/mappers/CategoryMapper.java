package api.m2.commerce.mappers;

import api.m2.commerce.entities.Category;
import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.categories.CategoryToAdd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import api.m2.commerce.records.categories.CategoryToUpdate;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryRecord toRecord(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Category toEntity(CategoryToAdd dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workspaceId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(CategoryToUpdate dto, @MappingTarget Category entity);
}
