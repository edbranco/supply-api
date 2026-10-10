package br.com.supply.supply_api.supplier.repository;

import br.com.supply.supply_api.supplier.entity.SupplierJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<SupplierJpaEntity, Long> {

    boolean existsByCnpj(String cnpj);
}
