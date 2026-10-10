package br.com.supply.supply_api.buyer.service;

import br.com.supply.supply_api.buyer.dto.BuyerResponse;
import br.com.supply.supply_api.buyer.dto.CreateBuyerRequest;

public interface CreateBuyerService {

    BuyerResponse create(CreateBuyerRequest createBuyerRequest);
}
