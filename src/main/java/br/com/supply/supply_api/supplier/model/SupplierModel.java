package br.com.supply.supply_api.supplier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierModel {

    private Long userId;
    private String email;
    private String senha;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private Boolean cnpjVerificado;
    private String descricao;
    private String atendimento;
    private String faturamentoPrazo;
    private String whatsapp;
    private String emailComercial;
    private String telefone;
    private String status;
    private String plano;
    private LocalDateTime trialFim;
    private Short completude;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String statusAssinatura;
    private LocalDate proximaCobranca;
    private String asaasCustomerId;
    private String asaasSubscriptionId;
}
