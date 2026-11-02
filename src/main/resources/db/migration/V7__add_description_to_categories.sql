ALTER TABLE categories
    ADD COLUMN description TEXT NOT NULL DEFAULT '';

ALTER TABLE categories
    ALTER COLUMN description DROP DEFAULT;