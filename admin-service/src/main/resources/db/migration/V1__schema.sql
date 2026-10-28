CREATE TABLE platform_settings(id BIGSERIAL PRIMARY KEY,setting_key TEXT,
setting_value TEXT,
updated_by TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());