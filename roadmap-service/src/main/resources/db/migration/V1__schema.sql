CREATE TABLE roadmaps(id BIGSERIAL PRIMARY KEY,student_id BIGINT,
version BIGINT,
target_role TEXT,
label TEXT,
status TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());