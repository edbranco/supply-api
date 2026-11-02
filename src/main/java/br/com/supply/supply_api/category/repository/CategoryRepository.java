package br.com.supply.supply_api.category.repository;

import br.com.supply.supply_api.buyer.repository.entity.CategoryJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    List<CategoryJpaEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
