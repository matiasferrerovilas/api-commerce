package api.m2.commerce.repositories;

import api.m2.commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByWorkspaceIdAndIsActiveTrue(Long workspaceId);

    List<Product> findByWorkspaceId(Long workspaceId);

    List<Product> findByCategoryIdAndIsActiveTrue(Long categoryId);

    Optional<Product> findBySkuAndWorkspaceId(String sku, Long workspaceId);

    boolean existsBySkuAndWorkspaceId(String sku, Long workspaceId);
}
