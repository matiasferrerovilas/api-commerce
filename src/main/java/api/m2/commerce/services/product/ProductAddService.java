package api.m2.commerce.services.product;

import api.m2.commerce.entities.Category;
import api.m2.commerce.entities.Product;
import api.m2.commerce.entities.Stock;
import api.m2.commerce.entities.User;
import api.m2.commerce.exceptions.BusinessException;
import api.m2.commerce.mappers.ProductMapper;
import api.m2.commerce.records.products.ProductRecord;
import api.m2.commerce.records.products.ProductToAdd;
import api.m2.commerce.records.products.ProductToUpdate;
import api.m2.commerce.repositories.ProductRepository;
import api.m2.commerce.repositories.StockRepository;
import api.m2.commerce.services.category.CategoryQueryService;
import api.m2.commerce.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductAddService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final ProductMapper productMapper;
    private final ProductQueryService productQueryService;
    private final CategoryQueryService categoryQueryService;
    private final UserService userService;

    public ProductRecord create(ProductToAdd dto) {
        this.validateUniqueSku(dto.sku(), dto.workspaceId());

        User currentUser = userService.getOrCreateUser();

        Product product = productMapper.toEntity(dto);
        product.setCreatedBy(currentUser);

        if (dto.categoryId() != null) {
            Category category = categoryQueryService.getById(dto.categoryId());
            product.setCategory(category);
        }

        Product saved = productRepository.save(product);

        this.createStock(saved, dto.initialStock());

        return productMapper.toRecord(saved);
    }

    public ProductRecord update(Long id, ProductToUpdate dto) {
        Product product = productQueryService.getById(id);

        if (dto.sku() != null && !dto.sku().equals(product.getSku())) {
            this.validateUniqueSku(dto.sku(), product.getWorkspaceId());
        }

        if (dto.categoryId() != null) {
            Category category = categoryQueryService.getById(dto.categoryId());
            product.setCategory(category);
        }

        productMapper.updateEntity(dto, product);
        Product saved = productRepository.save(product);
        return productMapper.toRecord(saved);
    }

    public void delete(Long id) {
        Product product = productQueryService.getById(id);
        product.setIsActive(false);
        productRepository.save(product);
    }

    private void createStock(Product product, Integer initialStock) {
        Stock stock = Stock.builder()
                .product(product)
                .quantity(initialStock != null ? initialStock : 0)
                .build();
        stockRepository.save(stock);
    }

    private void validateUniqueSku(String sku, Long workspaceId) {
        if (sku != null && productRepository.existsBySkuAndWorkspaceId(sku, workspaceId)) {
            throw new BusinessException("Ya existe un producto con ese SKU en este workspace");
        }
    }
}
