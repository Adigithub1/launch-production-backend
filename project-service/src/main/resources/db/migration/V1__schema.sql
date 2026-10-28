CREATE TABLE projects(id BIGSERIAL PRIMARY KEY,title TEXT,
stream TEXT,
level TEXT,
instructions TEXT,
rubric TEXT,
status TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());