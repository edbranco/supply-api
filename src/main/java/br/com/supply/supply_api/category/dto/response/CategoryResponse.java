package br.com.supply.supply_api.category.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryResponse(Long id, String nome, String slug, String description, String iconKey,
                               LocalDateTime createdAt, LocalDateTime updatedAt,
                               List<SubcategoryResponse> subcategories) {}
