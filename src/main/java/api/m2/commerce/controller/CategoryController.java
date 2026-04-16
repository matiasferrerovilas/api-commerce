package api.m2.commerce.controller;

import api.m2.commerce.records.categories.CategoryRecord;
import api.m2.commerce.records.categories.CategoryToAdd;
import api.m2.commerce.records.categories.CategoryToUpdate;
import api.m2.commerce.services.category.CategoryAddService;
import api.m2.commerce.services.category.CategoryQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryQueryService categoryQueryService;
    private final CategoryAddService categoryAddService;

    @GetMapping
    public ResponseEntity<List<CategoryRecord>> findByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(categoryQueryService.findByWorkspace(workspaceId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryRecord>> findAllByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(categoryQueryService.findAllByWorkspace(workspaceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRecord> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryQueryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryRecord> create(@Valid @RequestBody CategoryToAdd dto) {
        CategoryRecord created = categoryAddService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryRecord> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryToUpdate dto
    ) {
        return ResponseEntity.ok(categoryAddService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryAddService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
