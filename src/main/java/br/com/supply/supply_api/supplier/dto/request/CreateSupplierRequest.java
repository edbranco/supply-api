package br.com.supply.supply_api.supplier.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateSupplierRequest(

        @NotBlank(message = "email é obrigatório")
        @Email(message = "email deve ser válido")
        @Size(max = 255, message = "email deve ter no máximo 255 caracteres")
        String email,

        @NotBlank(message = "senha é obrigatória")
        @Size(min = 6, max = 100, message = "senha deve ter entre 6 e 100 caracteres")
        String senha,

        @NotBlank(message = "razaoSocial é obrigatória")
        @Size(max = 200, message = "razaoSocial deve ter no máximo 200 caracteres")
        String razaoSocial,

        @Size(max = 200, message = "nomeFantasia deve ter no máximo 200 caracteres")
        String nomeFantasia,

        @NotBlank(message = "cnpj é obrigatório")
        @Size(min = 14, max = 14, message = "cnpj deve ter exatamente 14 caracteres")
        String cnpj,

        String descricao,

        @Size(max = 100, message = "atendimento deve ter no máximo 100 caracteres")
        String atendimento,

        @Size(max = 100, message = "faturamentoPrazo deve ter no máximo 100 caracteres")
        String faturamentoPrazo,

        @Size(max = 20, message = "whatsapp deve ter no máximo 20 caracteres")
        String whatsapp,

        @Email(message = "emailComercial deve ser válido")
        @Size(max = 255, message = "emailComercial deve ter no máximo 255 caracteres")
        String emailComercial,

        @Size(max = 20, message = "telefone deve ter no máximo 20 caracteres")
        String telefone,

        @NotBlank(message = "status é obrigatório")
        @Size(max = 100, message = "status deve ter no máximo 100 caracteres")
        String status,

        @NotBlank(message = "plano é obrigatório")
        @Size(max = 50, message = "plano deve ter no máximo 50 caracteres")
        String plano,

        LocalDateTime trialFim,

        @Size(max = 8, message = "cep deve ter no máximo 8 caracteres")
        String cep,

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
        String uf,

        @NotBlank(message = "statusAssinatura é obrigatório")
        @Size(max = 50, message = "statusAssinatura deve ter no máximo 50 caracteres")
        String statusAssinatura,

        LocalDate proximaCobranca,

        @Size(max = 120, message = "asaasCustomerId deve ter no máximo 120 caracteres")
        String asaasCustomerId,

        @Size(max = 120, message = "asaasSubscriptionId deve ter no máximo 120 caracteres")
        String asaasSubscriptionId
) {
}
