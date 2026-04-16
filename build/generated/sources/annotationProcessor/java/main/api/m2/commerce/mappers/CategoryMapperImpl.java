package api.m2.commerce.mappers;

import api.m2.commerce.entities.Category;
import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.categories.CategoryToAdd;
import api.m2.commerce.records.categories.CategoryToUpdate;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryRecord toRecord(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        Boolean isActive = null;
        Long workspaceId = null;
        Instant createdAt = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();
        isActive = category.getIsActive();
        workspaceId = category.getWorkspaceId();
        createdAt = category.getCreatedAt();

        CategoryRecord categoryRecord = new CategoryRecord( id, name, description, isActive, workspaceId, createdAt );

        return categoryRecord;
    }

    @Override
    public Category toEntity(CategoryToAdd dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( dto.name() );
        category.description( dto.description() );
        category.workspaceId( dto.workspaceId() );

        category.isActive( true );

        return category.build();
    }

    @Override
    public void updateEntity(CategoryToUpdate dto, Category entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.name() != null ) {
            entity.setName( dto.name() );
        }
        if ( dto.description() != null ) {
            entity.setDescription( dto.description() );
        }
        if ( dto.isActive() != null ) {
            entity.setIsActive( dto.isActive() );
        }
    }
}
