package br.com.supply.supply_api.buyer.repository;

import br.com.supply.supply_api.buyer.repository.entity.SubcategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SubcategoryRepository extends JpaRepository<SubcategoryJpaEntity, Long> {
    List<SubcategoryJpaEntity> findByCategoryIdInOrderByCategoryIdAscNomeAsc(Collection<Long> categoryIds);
}
