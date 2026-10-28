CREATE TABLE notifications(id BIGSERIAL PRIMARY KEY,user_id BIGINT,
channel TEXT,
title TEXT,
message TEXT,
status TEXT,
read_flag BOOLEAN,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());