CREATE TABLE buyers (
    user_id         BIGINT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    nome            VARCHAR(150) NOT NULL,
    empresa         VARCHAR(150),
    cnpj            CHAR(14),
    whatsapp        VARCHAR(20),
    funcao          VARCHAR(100),
    subcategory_id  BIGINT REFERENCES subcategories (id),
    address_id      BIGINT REFERENCES addresses (id),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_buyers_cnpj ON buyers (cnpj);
CREATE INDEX idx_buyers_subcategory_id ON buyers (subcategory_id);
CREATE INDEX idx_buyers_address_id ON buyers (address_id);
