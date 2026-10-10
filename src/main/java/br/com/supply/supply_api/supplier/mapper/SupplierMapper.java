package br.com.supply.supply_api.supplier.mapper;

import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.entity.SubscriptionJpaEntity;
import br.com.supply.supply_api.supplier.entity.SupplierJpaEntity;
import br.com.supply.supply_api.supplier.model.SupplierModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierModel toModel(CreateSupplierRequest request);

    @Mapping(target = "cnpjVerificado", ignore = true)
    @Mapping(target = "completude", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SupplierJpaEntity toSupplierEntity(SupplierModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplierId", source = "userId")
    @Mapping(target = "status", source = "statusAssinatura")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SubscriptionJpaEntity toSubscriptionEntity(SupplierModel model);
}
