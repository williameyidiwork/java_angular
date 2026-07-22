create extension if not exists pgcrypto;

create table retention_policies (
    id uuid primary key default gen_random_uuid(),
    name varchar(120) not null,
    description varchar(500),
    retention_period_days integer not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    constraint uk_retention_policies_name unique (name),
    constraint chk_retention_policies_period_positive check (retention_period_days > 0)
);

create table records (
    id uuid primary key default gen_random_uuid(),
    external_id varchar(120) not null,
    name varchar(255) not null,
    status varchar(40) not null,
    retention_policy_id uuid,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    expires_at timestamptz,
    archived_at timestamptz,
    purged_at timestamptz,
    constraint uk_records_external_id unique (external_id),
    constraint fk_records_retention_policy foreign key (retention_policy_id) references retention_policies (id),
    constraint chk_records_status check (status in ('ACTIVE', 'ARCHIVED', 'PURGED', 'FAILED'))
);

create index idx_records_status on records (status);
create index idx_records_retention_policy_id on records (retention_policy_id);
create index idx_records_expires_at on records (expires_at);
