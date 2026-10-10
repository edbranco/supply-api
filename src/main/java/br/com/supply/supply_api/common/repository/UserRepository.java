package br.com.supply.supply_api.common.repository;

import br.com.supply.supply_api.common.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    boolean existsByEmail(String email);
}
