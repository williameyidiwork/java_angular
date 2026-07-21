# Interview Notes

This document turns each technical choice into language that sounds practical and professional.

## Project Pitch

This is an enterprise data governance platform. It manages business records through their lifecycle: active, archived, purged, or failed during processing. The system supports retention policies, audit logs, asynchronous events, ETL imports, retries, idempotency, observability, and cloud deployment.

## How To Explain The Architecture

Start simple:

> I designed this as a modular monolith first. The backend owns the domain rules for records, retention policies, archive and purge workflows, audit logs, and ETL jobs. PostgreSQL stores the source-of-truth data. Kafka is used later for asynchronous lifecycle events. Angular provides the admin portal.

Then add the reason:

> I would not start with microservices because the domain boundaries are still evolving. A modular monolith gives cleaner transactions, simpler debugging, and faster delivery while still keeping modules separated enough to split later if needed.

## Tool Vocabulary

Spring Boot:

> I use Spring Boot to build the REST API quickly with production features like dependency injection, validation, configuration, security, and Actuator health checks.

PostgreSQL:

> I use PostgreSQL because the core data is relational and needs consistency, constraints, indexes, and transactional updates.

Flyway:

> I use Flyway so database schema changes are version-controlled and repeatable across local, test, and production environments.

JPA and Hibernate:

> I use JPA and Hibernate for ORM mapping, repositories, and transaction management. For complex queries, I would still use explicit SQL or optimized repository methods when needed.

Spring Security and JWT:

> Spring Security handles authentication and authorization. JWT lets the frontend call protected APIs without storing server-side sessions.

Kafka:

> Kafka decouples the main record transaction from downstream workflows like audit consumers, notifications, or analytics. It also gives durable event delivery and retry patterns.

Docker Compose:

> Docker Compose gives developers a repeatable local environment for dependencies like PostgreSQL and Kafka.

Testcontainers:

> Testcontainers lets integration tests run against real infrastructure dependencies, such as PostgreSQL, instead of relying only on mocks.

Actuator:

> Actuator exposes health and metrics endpoints so the application can be monitored and checked by deployment platforms.

## Important Engineering Concepts

Idempotency:

> Idempotency means a workflow can be safely retried without causing duplicate side effects. For example, archiving the same record twice should not create two archive jobs.

Auditability:

> Auditability means the system records who performed an action, what changed, when it happened, and why. This is important for regulated or enterprise systems.

Retention policy:

> A retention policy defines how long data should be kept before it becomes eligible for archive or purge.

Dead-letter handling:

> A dead-letter queue or topic stores messages that failed too many times so they can be investigated without blocking the whole system.

Correlation ID:

> A correlation ID connects logs, audit records, API requests, and events from the same operation so engineers can trace behavior across the system.

## First Interview Story

Use this when someone asks what you are building:

> I am building an enterprise data governance platform using Java, Spring Boot, Angular, PostgreSQL, Kafka, and Docker. The application manages records from creation through retention, archive, and purge. I am focusing on real production concerns like audit logs, idempotent workflows, retries, ETL failure handling, health checks, metrics, and eventually cloud deployment on AWS.

## What To Avoid Saying

Avoid:

> It is just a CRUD app.

Say:

> The first slice starts with CRUD, but the project is really about data lifecycle governance, auditability, and reliable background processing.

Avoid:

> Kafka is for making things faster.

Say:

> Kafka is for decoupling workflows and handling asynchronous event processing reliably.

Avoid:

> Docker is only for deployment.

Say:

> Docker also gives us repeatable local development environments, which reduces setup differences across machines.
