package br.com.supply.supply_api.category.service;

import br.com.supply.supply_api.category.dto.request.CreateCategoryRequest;
import br.com.supply.supply_api.category.dto.response.CategoryResponse;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> create(List<CreateCategoryRequest> requests);
    List<CategoryResponse> findAll(String nome, int limite);
}
