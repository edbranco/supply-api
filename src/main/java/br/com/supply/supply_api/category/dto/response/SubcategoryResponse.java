package br.com.supply.supply_api.category.dto.response;

import java.time.LocalDateTime;

public record SubcategoryResponse(
        Long id,
        String nome,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
