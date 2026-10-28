CREATE TABLE student_profiles (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL UNIQUE,

    name TEXT,

    email TEXT UNIQUE,

    phone TEXT,

    stream TEXT,

    branch TEXT,

    specialization TEXT,

    target_role TEXT,

    designation TEXT,

    college TEXT,

    graduation_year TEXT,

    skills TEXT,

    interests TEXT,

    resume_key TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);