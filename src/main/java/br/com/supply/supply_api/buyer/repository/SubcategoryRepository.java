package br.com.supply.supply_api.buyer.repository;

import br.com.supply.supply_api.buyer.repository.entity.SubcategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<SubcategoryJpaEntity, Long> {
}
