package br.com.supply.supply_api.supplier.dto.response;

import java.util.List;

public record SupplierResponse(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String descricao,
        String atendimento,
        String faturamentoPrazo,
        String whatsapp,
        String emailComercial,
        String telefone,
        String status,
        String plano,
        String cidade,
        String uf,
        List<String> categorias
) {
}
