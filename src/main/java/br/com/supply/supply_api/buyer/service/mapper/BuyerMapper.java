package br.com.supply.supply_api.buyer.service.mapper;

import br.com.supply.supply_api.buyer.dto.BuyerResponse;
import br.com.supply.supply_api.buyer.dto.CreateBuyerRequest;
import br.com.supply.supply_api.buyer.repository.entity.BuyerJpaEntity;
import br.com.supply.supply_api.buyer.service.model.BuyerModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    BuyerJpaEntity buyerModelToBuyerJpaEntity(BuyerModel buyerModel);

    BuyerResponse buyerJpaEntityToBuyerResponse(BuyerJpaEntity buyerJpaEntity);

    BuyerModel buyerRequestToBuyerModel(CreateBuyerRequest createBuyerRequest);
}
