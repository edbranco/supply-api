package br.com.supply.supply_api.category.controller;

import br.com.supply.supply_api.category.dto.request.CreateCategoryRequest;
import br.com.supply.supply_api.category.dto.response.CategoryResponse;
import br.com.supply.supply_api.category.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<List<CategoryResponse>> create(
            @Valid @RequestBody List<@Valid CreateCategoryRequest> requests
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(requests));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "50") @Min(value = 1, message = "limite deve ser no mínimo 1") @Max(value = 100, message = "limite deve ser no máximo 100") int limite) {
        return ResponseEntity.ok(categoryService.findAll(nome, limite));
    }
}
