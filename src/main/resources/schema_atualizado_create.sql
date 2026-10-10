-- schema.sql
-- Supply Platform Schema (MVP)

SET search_path TO teste;
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL UNIQUE,
    slug VARCHAR(120) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE subcategories (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    nome VARCHAR(120) NOT NULL,
    slug VARCHAR(120) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(category_id,nome),
    UNIQUE(category_id,slug)
);

CREATE TABLE buyers (
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    nome VARCHAR(150) NOT NULL,
    empresa VARCHAR(150),
    cnpj VARCHAR(14),
    whatsapp VARCHAR(20),
    funcao VARCHAR(100),
    subcategory_id BIGINT REFERENCES subcategories(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	cep VARCHAR(8),
    logradouro VARCHAR(150),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);

CREATE TABLE suppliers (
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    razao_social VARCHAR(200) NOT NULL,
    nome_fantasia VARCHAR(200),
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    cnpj_verificado BOOLEAN DEFAULT FALSE,
    descricao TEXT,
    atendimento VARCHAR(100),
    faturamento_prazo VARCHAR(100),
    whatsapp VARCHAR(20),
    email_comercial VARCHAR(255),
    telefone VARCHAR(20),
    status VARCHAR(100) NOT NULL,
    plano VARCHAR(50) NOT NULL,
    trial_fim TIMESTAMP,
    completude SMALLINT DEFAULT 0 CHECK(completude BETWEEN 0 AND 100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	cep VARCHAR(8),
    logradouro VARCHAR(150),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);

CREATE TABLE supplier_subcategories (
    supplier_id BIGINT NOT NULL REFERENCES suppliers(user_id) ON DELETE CASCADE,
    subcategory_id BIGINT NOT NULL REFERENCES subcategories(id) ON DELETE CASCADE,
    principal BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(supplier_id,subcategory_id)
);

CREATE TABLE leads (
    id BIGSERIAL PRIMARY KEY,
    buyer_id BIGINT NOT NULL REFERENCES buyers(user_id),
    supplier_id BIGINT NOT NULL REFERENCES suppliers(user_id),
    canal VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE profile_views (
    supplier_id BIGINT NOT NULL REFERENCES suppliers(user_id) ON DELETE CASCADE,
    data DATE NOT NULL,
    contagem INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY(supplier_id,data)
);

CREATE TABLE subscriptions (
    id BIGSERIAL PRIMARY KEY,
    supplier_id BIGINT UNIQUE NOT NULL REFERENCES suppliers(user_id) ON DELETE CASCADE,
    asaas_customer_id VARCHAR(120),
    asaas_subscription_id VARCHAR(120),
    plano VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    proxima_cobranca DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_suppliers_status ON suppliers(status);
CREATE INDEX idx_suppliers_plano ON suppliers(plano);
CREATE INDEX idx_subcategories_category ON subcategories(category_id);
CREATE INDEX idx_supplier_subcategories_subcategory ON supplier_subcategories(subcategory_id);
CREATE INDEX idx_leads_supplier ON leads(supplier_id);
CREATE INDEX idx_leads_buyer ON leads(buyer_id);
CREATE INDEX idx_profile_views_data ON profile_views(data);

CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_users_updated BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_addresses_updated BEFORE UPDATE ON addresses FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_categories_updated BEFORE UPDATE ON categories FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_subcategories_updated BEFORE UPDATE ON subcategories FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_buyers_updated BEFORE UPDATE ON buyers FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_suppliers_updated BEFORE UPDATE ON suppliers FOR EACH ROW EXECUTE FUNCTION set_updated_at();
CREATE TRIGGER trg_subscriptions_updated BEFORE UPDATE ON subscriptions FOR EACH ROW EXECUTE FUNCTION set_updated_at();
