package br.com.supply.supply_api.supplier.service;

import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.dto.response.CreateSupplierResponse;
import br.com.supply.supply_api.supplier.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    CreateSupplierResponse createSupplier(CreateSupplierRequest request);

    List<SupplierResponse> findSuppliers(List<String> categorias, String termo);
}
