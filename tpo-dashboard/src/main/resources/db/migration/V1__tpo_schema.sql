create table if not exists placement_cycles (
  id bigserial primary key,
  college_id bigint not null,
  name varchar(80) not null,
  start_date date not null,
  end_date date not null,
  active boolean not null default false,
  unique (college_id, name)
);

create table if not exists student_placement_profiles (
  id bigserial primary key,
  college_id bigint not null,
  cycle_id bigint not null references placement_cycles(id),
  user_id bigint not null,
  full_name varchar(160) not null,
  email varchar(180) not null,
  faculty varchar(80) not null,
  course varchar(80) not null,
  branch varchar(80),
  graduation_year int not null,
  cgpa numeric(4,2),
  active_backlogs int not null default 0,
  profile_score int not null default 0 check (profile_score between 0 and 100),
  aptitude_score int not null default 0 check (aptitude_score between 0 and 100),
  technical_score int not null default 0 check (technical_score between 0 and 100),
  project_score int not null default 0 check (project_score between 0 and 100),
  resume_score int not null default 0 check (resume_score between 0 and 100),
  interview_score int not null default 0 check (interview_score between 0 and 100),
  readiness_score int not null default 0 check (readiness_score between 0 and 100),
  verification_status varchar(30) not null default 'PENDING',
  updated_at timestamptz not null default now(),
  unique (cycle_id, user_id)
);

create table if not exists companies (
  id bigserial primary key,
  college_id bigint not null,
  name varchar(160) not null,
  industry varchar(100),
  website varchar(255),
  relationship_status varchar(30) not null default 'PROSPECT',
  last_contact_at timestamptz,
  unique (college_id, name)
);

create table if not exists opportunities (
  id bigserial primary key,
  college_id bigint not null,
  cycle_id bigint not null references placement_cycles(id),
  company_id bigint not null references companies(id),
  title varchar(160) not null,
  employment_type varchar(30) not null default 'FULL_TIME',
  location varchar(120),
  min_cgpa numeric(4,2),
  max_backlogs int,
  ctc_min_lpa numeric(8,2),
  ctc_max_lpa numeric(8,2),
  application_deadline timestamptz,
  status varchar(30) not null default 'DRAFT',
  created_at timestamptz not null default now()
);

create table if not exists opportunity_eligible_faculties (
  opportunity_id bigint not null references opportunities(id) on delete cascade,
  faculty varchar(80) not null,
  primary key (opportunity_id, faculty)
);
create table if not exists opportunity_eligible_branches (
  opportunity_id bigint not null references opportunities(id) on delete cascade,
  branch varchar(80) not null,
  primary key (opportunity_id, branch)
);
create table if not exists opportunity_required_skills (
  opportunity_id bigint not null references opportunities(id) on delete cascade,
  skill varchar(80) not null,
  primary key (opportunity_id, skill)
);

create table if not exists placement_drives (
  id bigserial primary key,
  opportunity_id bigint not null references opportunities(id),
  college_id bigint not null,
  cycle_id bigint not null references placement_cycles(id),
  drive_date timestamptz not null,
  mode varchar(20) not null default 'ONSITE',
  venue varchar(160),
  current_stage varchar(30) not null default 'REGISTRATION',
  status varchar(30) not null default 'SCHEDULED',
  created_at timestamptz not null default now()
);

create table if not exists applications (
  id bigserial primary key,
  opportunity_id bigint not null references opportunities(id),
  drive_id bigint references placement_drives(id),
  student_profile_id bigint not null references student_placement_profiles(id),
  stage varchar(30) not null default 'APPLIED',
  applied_at timestamptz not null default now(),
  updated_at timestamptz not null default now(),
  unique (opportunity_id, student_profile_id)
);

create table if not exists offers (
  id bigserial primary key,
  application_id bigint not null unique references applications(id),
  ctc_lpa numeric(8,2) not null,
  status varchar(30) not null default 'ISSUED',
  offered_at timestamptz not null default now(),
  joining_date date
);

create table if not exists tpo_action_items (
  id bigserial primary key,
  college_id bigint not null,
  cycle_id bigint not null references placement_cycles(id),
  type varchar(40) not null,
  severity varchar(20) not null,
  title varchar(200) not null,
  description varchar(500),
  entity_type varchar(40),
  entity_id bigint,
  status varchar(20) not null default 'OPEN',
  due_at timestamptz,
  created_at timestamptz not null default now()
);

create index if not exists idx_spp_scope on student_placement_profiles(college_id, cycle_id);
create index if not exists idx_spp_readiness on student_placement_profiles(cycle_id, readiness_score);
create index if not exists idx_app_stage on applications(stage, opportunity_id);
create index if not exists idx_drive_scope_date on placement_drives(college_id, cycle_id, drive_date);
create index if not exists idx_offer_status on offers(status);
create index if not exists idx_action_open on tpo_action_items(college_id, cycle_id, status);
