CREATE TABLE addresses (
    id         BIGSERIAL PRIMARY KEY,
    street     VARCHAR(255),
    city       VARCHAR(100),
    state      VARCHAR(50),
    zip_code   VARCHAR(20),
    created_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
