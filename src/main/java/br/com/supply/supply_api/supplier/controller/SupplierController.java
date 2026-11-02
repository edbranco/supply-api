package br.com.supply.supply_api.supplier.controller;

import br.com.supply.supply_api.supplier.dto.request.CreateSupplierRequest;
import br.com.supply.supply_api.supplier.dto.response.CreateSupplierResponse;
import br.com.supply.supply_api.supplier.dto.response.SupplierResponse;
import br.com.supply.supply_api.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<CreateSupplierResponse> createSupplier(
            @Valid @RequestBody CreateSupplierRequest request
    ) {
        CreateSupplierResponse response = supplierService.createSupplier(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> findSuppliers(
            @RequestParam(name = "categoria", required = false) List<String> categorias,
            @RequestParam(required = false) String termo
    ) {
        return ResponseEntity.ok(supplierService.findSuppliers(categorias, termo));
    }
}
