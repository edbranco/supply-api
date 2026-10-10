package br.com.supply.supply_api.supplier.service;

import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.dto.response.CreateSupplierResponse;

public interface SupplierService {

    CreateSupplierResponse createSupplier(CreateSupplierRequest request);
}
