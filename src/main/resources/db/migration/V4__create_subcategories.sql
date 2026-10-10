CREATE TABLE subcategories (
    id          BIGSERIAL PRIMARY KEY,
    category_id BIGINT       NOT NULL REFERENCES categories (id) ON DELETE CASCADE,
    nome        VARCHAR(120) NOT NULL,
    slug        VARCHAR(120) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (category_id, nome),
    UNIQUE (category_id, slug)
);
