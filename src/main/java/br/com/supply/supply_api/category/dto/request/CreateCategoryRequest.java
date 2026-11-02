package br.com.supply.supply_api.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "nome é obrigatório")
        @Size(max = 120, message = "nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "slug é obrigatório")
        @Size(max = 120, message = "slug deve ter no máximo 120 caracteres")
        String slug,

        @NotBlank(message = "description é obrigatório")
        String description,

        @Size(max = 255, message = "iconKey deve ter no máximo 255 caracteres")
        String iconKey
) {}