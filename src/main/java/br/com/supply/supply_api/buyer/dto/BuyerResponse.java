package br.com.supply.supply_api.buyer.dto;

import java.time.LocalDateTime;

public record BuyerResponse(
        Long userId,
        String nome,
        String empresa,
        String cnpj,
        String whatsapp,
        String funcao,
        Long subcategoryId,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
