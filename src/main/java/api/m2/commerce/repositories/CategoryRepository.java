package api.m2.commerce.repositories;

import api.m2.commerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByWorkspaceIdAndIsActiveTrue(Long workspaceId);

    List<Category> findByWorkspaceId(Long workspaceId);

    boolean existsByNameAndWorkspaceId(String name, Long workspaceId);
}
