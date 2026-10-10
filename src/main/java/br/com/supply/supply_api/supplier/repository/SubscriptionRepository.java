package br.com.supply.supply_api.supplier.repository;

import br.com.supply.supply_api.supplier.entity.SubscriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionJpaEntity, Long> {
}
