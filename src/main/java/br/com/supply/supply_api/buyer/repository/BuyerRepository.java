package br.com.supply.supply_api.buyer.repository;

import br.com.supply.supply_api.buyer.repository.entity.BuyerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<BuyerJpaEntity, Long> {
}
