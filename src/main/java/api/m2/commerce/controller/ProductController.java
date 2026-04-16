package api.m2.commerce.controller;

import api.m2.commerce.records.products.ProductRecord;
import api.m2.commerce.records.products.ProductToAdd;
import api.m2.commerce.records.products.ProductToUpdate;
import api.m2.commerce.services.product.ProductAddService;
import api.m2.commerce.services.product.ProductQueryService;
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
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductAddService productAddService;

    @GetMapping
    public ResponseEntity<List<ProductRecord>> findByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(productQueryService.findByWorkspace(workspaceId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductRecord>> findAllByWorkspace(@RequestParam Long workspaceId) {
        return ResponseEntity.ok(productQueryService.findAllByWorkspace(workspaceId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductRecord>> findByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productQueryService.findByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRecord> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productQueryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductRecord> create(@Valid @RequestBody ProductToAdd dto) {
        ProductRecord created = productAddService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductRecord> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductToUpdate dto
    ) {
        return ResponseEntity.ok(productAddService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productAddService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
