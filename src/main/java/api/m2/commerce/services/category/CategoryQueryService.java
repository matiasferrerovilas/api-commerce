package api.m2.commerce.services.category;

import api.m2.commerce.entities.Category;
import api.m2.commerce.exceptions.EntityNotFoundException;
import api.m2.commerce.mappers.CategoryMapper;
import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryRecord> findByWorkspace(Long workspaceId) {
        return categoryRepository.findByWorkspaceIdAndIsActiveTrue(workspaceId).stream()
                .map(categoryMapper::toRecord)
                .toList();
    }

    public List<CategoryRecord> findAllByWorkspace(Long workspaceId) {
        return categoryRepository.findByWorkspaceId(workspaceId).stream()
                .map(categoryMapper::toRecord)
                .toList();
    }

    public CategoryRecord findById(Long id) {
        Category category = this.getById(id);
        return categoryMapper.toRecord(category);
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría", id));
    }
}
