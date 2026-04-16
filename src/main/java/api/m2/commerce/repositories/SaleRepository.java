package api.m2.commerce.repositories;

import api.m2.commerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByWorkspaceIdOrderByDateDesc(Long workspaceId);

    List<Sale> findByWorkspaceIdAndDateBetweenOrderByDateDesc(
            Long workspaceId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Sale> findByWorkspaceIdAndDateOrderByCreatedAtDesc(Long workspaceId, LocalDate date);

    @Query("""
            SELECT s FROM Sale s
            WHERE s.workspaceId = :workspaceId
            AND s.date = :date
            """)
    List<Sale> findDailySales(@Param("workspaceId") Long workspaceId, @Param("date") LocalDate date);
}
