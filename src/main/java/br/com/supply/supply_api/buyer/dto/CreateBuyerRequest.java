package br.com.supply.supply_api.buyer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateBuyerRequest(
        @NotNull(message = "senha é obrigatório")
        String senha,

        @NotBlank(message = "nome é obrigatório")
        @Size(max = 150, message = "nome deve ter no máximo 150 caracteres")
        String nome,

        @Size(max = 150, message = "empresa deve ter no máximo 150 caracteres")
        String empresa,

        String cnpj,

        String email,

        @Size(max = 20, message = "whatsapp deve ter no máximo 20 caracteres")
        String whatsapp,

        @Size(max = 100, message = "funcao deve ter no máximo 100 caracteres")
        String funcao,

        @Size(max = 150, message = "logradouro deve ter no máximo 150 caracteres")
        String logradouro,

        @Size(max = 20, message = "numero deve ter no máximo 20 caracteres")
        String numero,

        @Size(max = 100, message = "complemento deve ter no máximo 100 caracteres")
        String complemento,

        @Size(max = 100, message = "bairro deve ter no máximo 100 caracteres")
        String bairro,

        @Size(max = 100, message = "cidade deve ter no máximo 100 caracteres")
        String cidade,

        @Size(max = 2, message = "uf deve ter no máximo 2 caracteres")
        String uf
) {
}
