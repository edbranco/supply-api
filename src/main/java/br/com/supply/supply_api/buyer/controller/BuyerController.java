package br.com.supply.supply_api.buyer.controller;

import br.com.supply.supply_api.buyer.dto.BuyerResponse;
import br.com.supply.supply_api.buyer.dto.CreateBuyerRequest;
import br.com.supply.supply_api.buyer.service.CreateBuyerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {

    private final CreateBuyerService createBuyerService;

    public BuyerController(
            CreateBuyerService createBuyerService
    ) {
        this.createBuyerService = createBuyerService;
    }

    @PostMapping
    public ResponseEntity<BuyerResponse> createBuyer(@Valid @RequestBody CreateBuyerRequest request) {
        BuyerResponse response = createBuyerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
