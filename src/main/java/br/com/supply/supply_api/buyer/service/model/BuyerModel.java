package br.com.supply.supply_api.buyer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyerModel {
    private Long userId;

    private String senha;

    private String nome;

    private String empresa;

    private String cnpj;

    private String email;

    private String whatsapp;

    private String funcao;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;
    private String cidade;

    private String uf;
}
