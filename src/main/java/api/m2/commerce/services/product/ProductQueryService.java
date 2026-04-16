package api.m2.commerce.services.product;

import api.m2.commerce.entities.Product;
import api.m2.commerce.exceptions.EntityNotFoundException;
import api.m2.commerce.mappers.ProductMapper;
import api.m2.commerce.records.products.ProductRecord;
import api.m2.commerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductRecord> findByWorkspace(Long workspaceId) {
        return productRepository.findByWorkspaceIdAndIsActiveTrue(workspaceId).stream()
                .map(productMapper::toRecord)
                .toList();
    }

    public List<ProductRecord> findAllByWorkspace(Long workspaceId) {
        return productRepository.findByWorkspaceId(workspaceId).stream()
                .map(productMapper::toRecord)
                .toList();
    }

    public List<ProductRecord> findByCategory(Long categoryId) {
        return productRepository.findByCategoryIdAndIsActiveTrue(categoryId).stream()
                .map(productMapper::toRecord)
                .toList();
    }

    public ProductRecord findById(Long id) {
        Product product = this.getById(id);
        return productMapper.toRecord(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto", id));
    }
}
