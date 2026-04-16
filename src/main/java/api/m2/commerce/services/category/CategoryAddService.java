package api.m2.commerce.services.category;

import api.m2.commerce.entities.Category;
import api.m2.commerce.entities.User;
import api.m2.commerce.exceptions.BusinessException;
import api.m2.commerce.mappers.CategoryMapper;
import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.categories.CategoryToAdd;
import api.m2.commerce.records.categories.CategoryToUpdate;
import api.m2.commerce.repositories.CategoryRepository;
import api.m2.commerce.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryAddService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryQueryService categoryQueryService;
    private final UserService userService;

    public CategoryRecord create(CategoryToAdd dto) {
        this.validateUniqueName(dto.name(), dto.workspaceId());

        User currentUser = userService.getOrCreateUser();

        Category category = categoryMapper.toEntity(dto);
        category.setCreatedBy(currentUser);

        Category saved = categoryRepository.save(category);
        return categoryMapper.toRecord(saved);
    }

    public CategoryRecord update(Long id, CategoryToUpdate dto) {
        Category category = categoryQueryService.getById(id);

        if (dto.name() != null && !dto.name().equals(category.getName())) {
            this.validateUniqueName(dto.name(), category.getWorkspaceId());
        }

        categoryMapper.updateEntity(dto, category);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toRecord(saved);
    }

    public void delete(Long id) {
        Category category = categoryQueryService.getById(id);
        category.setIsActive(false);
        categoryRepository.save(category);
    }

    private void validateUniqueName(String name, Long workspaceId) {
        if (categoryRepository.existsByNameAndWorkspaceId(name, workspaceId)) {
            throw new BusinessException("Ya existe una categoría con ese nombre en este workspace");
        }
    }
}
