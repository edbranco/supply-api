package br.com.supply.supply_api.supplier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
public class SupplierJpaEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "razao_social", nullable = false, length = 200)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 200)
    private String nomeFantasia;

    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(name = "cnpj_verificado")
    private Boolean cnpjVerificado;

    @Column(columnDefinition = "text")
    private String descricao;

    @Column(length = 100)
    private String atendimento;

    @Column(name = "faturamento_prazo", length = 100)
    private String faturamentoPrazo;

    @Column(length = 20)
    private String whatsapp;

    @Column(name = "email_comercial", length = 255)
    private String emailComercial;

    @Column(length = 20)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String status;

    @Column(nullable = false, length = 50)
    private String plano;

    @Column(name = "trial_fim")
    private LocalDateTime trialFim;

    @Column
    private Short completude;

    @Column(length = 8)
    private String cep;

    @Column(length = 150)
    private String logradouro;

    @Column(length = 20)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        if (updatedAt == null) {
            updatedAt = now;
        }
        if (cnpjVerificado == null) {
            cnpjVerificado = false;
        }
        if (completude == null) {
            completude = 0;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
