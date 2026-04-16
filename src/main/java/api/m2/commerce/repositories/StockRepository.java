package api.m2.commerce.repositories;

import api.m2.commerce.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);

    @Query("SELECT s FROM Stock s JOIN s.product p WHERE p.workspaceId = :workspaceId")
    List<Stock> findByWorkspaceId(@Param("workspaceId") Long workspaceId);

    @Query("SELECT s FROM Stock s JOIN s.product p WHERE p.workspaceId = :workspaceId AND s.quantity <= s.minStock")
    List<Stock> findLowStockByWorkspaceId(@Param("workspaceId") Long workspaceId);
}
