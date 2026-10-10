package br.com.supply.supply_api.buyer.repository;

import br.com.supply.supply_api.buyer.repository.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
}
