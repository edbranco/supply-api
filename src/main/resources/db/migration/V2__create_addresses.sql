CREATE TABLE addresses (
    id          BIGSERIAL PRIMARY KEY,
    cep         CHAR(8),
    logradouro  VARCHAR(150),
    numero      VARCHAR(20),
    complemento VARCHAR(100),
    bairro      VARCHAR(100),
    cidade      VARCHAR(100),
    uf          CHAR(2),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
