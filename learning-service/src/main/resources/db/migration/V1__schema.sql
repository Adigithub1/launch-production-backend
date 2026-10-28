CREATE TABLE courses(id BIGSERIAL PRIMARY KEY,title TEXT,
category TEXT,
stream TEXT,
description TEXT,
difficulty TEXT,
status TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());