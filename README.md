# Enterprise Data Governance Platform

An enterprise full-stack application for managing business records, applying retention policies, archiving stale data, purging expired data, tracking audit logs, and processing asynchronous events.

The project is intentionally built in small increments. Each increment introduces a practical engineering concept, verifies it locally, and ends with a Git commit.

## Product Goal

Organizations often need to keep business records for a defined period, archive them when they become inactive, purge them when they expire, and prove what happened through audit logs. This platform models that lifecycle.

The first version focuses on a local developer environment and a clear backend domain model. Later versions add asynchronous event processing, frontend administration screens, observability, and cloud deployment.

## Core Capabilities

- Manage records with statuses, search, filters, pagination, and sorting.
- Define retention policies and calculate expiration dates.
- Archive and purge records using idempotent workflows.
- Track audit logs for important actions and status transitions.
- Import records through ETL jobs with retryable failures.
- Publish and consume lifecycle events asynchronously.
- Provide operational visibility through health checks, metrics, and structured logs.

## Technology Stack

Backend:

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA and Hibernate
- PostgreSQL
- Flyway
- Kafka or Redpanda for local event streaming
- JUnit 5, Mockito, and Testcontainers
- OpenAPI/Swagger
- Spring Boot Actuator

Frontend:

- Angular
- TypeScript
- Angular Router
- HttpClient
- Reactive Forms
- Angular Material
- RxJS
- JWT interceptor and route guards

Local platform:

- Docker
- Docker Compose

## Local Development

### PostgreSQL

The local PostgreSQL database runs through Docker Compose.

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Check service status:

```bash
docker compose ps
```

Verify PostgreSQL readiness:

```bash
docker compose exec postgres pg_isready -U governance -d governance
```

Stop local services:

```bash
docker compose down
```

The named Docker volume `governance-platform_postgres_data` preserves database files between container restarts.

Optional environment overrides can be copied from `.env.example` into a local `.env` file. The `.env` file is ignored by Git.

Cloud path:

- Amazon ECS Fargate or EKS for backend containers
- Amazon RDS for PostgreSQL
- Amazon S3 for archived payloads
- Amazon SQS or MSK for asynchronous processing
- CloudWatch for logs and metrics
- S3 and CloudFront for the Angular frontend
- Secrets Manager for credentials

## Incremental Build Workflow

Every step follows the same pattern:

1. Define the professional engineering concept.
2. Implement one focused slice.
3. Run a local verification command.
4. Explain the design in interview language.
5. Commit the checkpoint before moving to the next step.

Command notes are tracked in [docs/command-log.md](docs/command-log.md) so the terminal workflow is also part of the learning path.

## First Milestones

1. Project charter and architecture notes.
2. Spring Boot backend skeleton.
3. Docker Compose PostgreSQL environment.
4. Flyway database schema.
5. Records API with pagination and filtering.
6. Retention policies.
7. Audit logging.
8. Authentication and authorization.
9. Archive and purge workflows.
10. Kafka lifecycle events.
11. ETL imports and retries.
12. Observability.
13. Angular admin portal.
14. CI/CD and cloud deployment.

## Interview Summary

This project demonstrates how to design and build an enterprise data lifecycle platform. It covers API design, persistence, data governance, security, asynchronous processing, idempotency, retries, auditability, observability, testing, containerization, and cloud deployment.
