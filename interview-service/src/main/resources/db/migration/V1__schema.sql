CREATE TABLE interview_sessions(id BIGSERIAL PRIMARY KEY,student_id BIGINT,
round_type TEXT,
status TEXT,
score INTEGER,
weaknesses TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());