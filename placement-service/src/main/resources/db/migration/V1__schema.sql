CREATE TABLE jobs(id BIGSERIAL PRIMARY KEY,company TEXT,
role TEXT,
location TEXT,
mode TEXT,
package_range TEXT,
status TEXT,created_at TIMESTAMPTZ NOT NULL DEFAULT now(),updated_at TIMESTAMPTZ NOT NULL DEFAULT now());