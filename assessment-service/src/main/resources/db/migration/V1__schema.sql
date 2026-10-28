CREATE TABLE assessments(id BIGSERIAL PRIMARY KEY,title TEXT,
round_type TEXT,
duration_minutes BIGINT,
pass_score BIGINT,
status TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());