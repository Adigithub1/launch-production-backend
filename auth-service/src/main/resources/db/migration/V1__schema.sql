CREATE TABLE users(id BIGSERIAL PRIMARY KEY,email TEXT,
password_hash TEXT,
role TEXT,
status TEXT,
device_id TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());