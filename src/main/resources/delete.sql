BEGIN;

-- ==========================
-- DROP TABLES
-- ==========================

DROP TABLE IF EXISTS supplier_subcategories CASCADE;
DROP TABLE IF EXISTS supplier_categories CASCADE;
DROP TABLE IF EXISTS supplier_addresses CASCADE;
DROP TABLE IF EXISTS buyer_addresses CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS buyers CASCADE;
DROP TABLE IF EXISTS subcategories CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ==========================
-- DROP ENUMS
-- ==========================

DROP TYPE IF EXISTS supplier_status CASCADE;
DROP TYPE IF EXISTS plan_type CASCADE;
DROP TYPE IF EXISTS user_type CASCADE;

COMMIT;