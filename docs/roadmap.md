# Roadmap

The roadmap is intentionally incremental. Each step should be understood, verified, and committed before the next one begins.

## Step 0: Project Charter And Architecture

Goal:

- Explain what the platform does.
- Name the major tools.
- Describe the local and cloud architecture.
- Prepare interview language.

Commit:

```text
chore: add project charter and architecture plan
```

## Step 1: Spring Boot Backend Skeleton

Goal:

- Create the Java 21 Spring Boot application.
- Add health checks through Spring Boot Actuator.
- Add a simple root endpoint or version endpoint.

Tools:

- Java 21
- Spring Boot
- Maven
- Spring Web
- Actuator

Verification:

```text
./mvnw test
./mvnw spring-boot:run
```

Interview focus:

- Why Spring Boot?
- What is an API?
- What does a health check prove?

## Step 2: Local PostgreSQL With Docker Compose

Goal:

- Add PostgreSQL as the local database.
- Configure the backend to connect to it.

Tools:

- Docker
- Docker Compose
- PostgreSQL

Verification:

```text
docker compose up -d
./mvnw test
```

Interview focus:

- Why use Docker Compose locally?
- Why PostgreSQL for this domain?

## Step 3: Flyway Schema

Goal:

- Create the first database migration.
- Add records, retention policies, and audit logs.

Tools:

- Flyway
- SQL migrations
- PostgreSQL constraints and indexes

Interview focus:

- Why migrations matter.
- Why database constraints still matter even when the backend validates input.

## Step 4: Records API

Goal:

- Create records.
- Read records.
- Search and filter records.
- Add pagination and sorting.

Tools:

- Spring MVC
- DTOs
- JPA repositories
- Bean Validation

Interview focus:

- DTOs versus entities.
- Pagination for large datasets.
- API design basics.

## Step 5: Retention Policies

Goal:

- Create retention policies.
- Assign policies to records.
- Calculate expiration dates.

Interview focus:

- Where business logic belongs.
- Why retention rules need tests.

## Step 6: Audit Logging

Goal:

- Record important changes.
- Include old status, new status, user, reason, timestamp, and correlation ID.

Interview focus:

- Auditability.
- Traceability.
- Regulatory-style systems.

## Step 7: Security

Goal:

- Add login.
- Generate JWTs.
- Enforce roles.

Interview focus:

- Authentication versus authorization.
- Role-based access control.
- Why not store passwords in plain text.

## Step 8: Archive And Purge

Goal:

- Archive expired records.
- Purge records after retention.
- Prevent duplicate processing.

Interview focus:

- Idempotency.
- Transactions.
- Status transitions.

## Step 9: Events

Goal:

- Publish record lifecycle events.
- Consume events for audit or notification workflows.
- Add retry and dead-letter handling.

Interview focus:

- Async processing.
- Event-driven architecture.
- Dead-letter queues.

## Step 10: ETL Jobs

Goal:

- Import CSV or JSON.
- Validate and transform records.
- Track failed records.
- Retry failed jobs.

Interview focus:

- Batch processing.
- Retryable versus permanent failures.
- Resumability.

## Step 11: Observability

Goal:

- Add structured logs.
- Track health and metrics.
- Add request correlation.

Interview focus:

- Monitoring versus logging.
- Metrics and latency.
- Debugging production issues.

## Step 12: Angular Admin Portal

Goal:

- Build login, dashboard, records, policy, ETL, and audit pages.

Interview focus:

- Component architecture.
- Reactive forms.
- Route guards.
- HTTP interceptors.

## Step 13: CI/CD

Goal:

- Add automated backend and frontend checks.
- Build Docker image.

Interview focus:

- Why CI protects the main branch.
- What should run before deployment.

## Step 14: Cloud Deployment

Goal:

- Deploy the frontend and backend to AWS.
- Use managed database, logs, secrets, and storage.

Interview focus:

- ECS versus EKS.
- RDS for managed PostgreSQL.
- S3 and CloudFront for frontend hosting.
- Secrets Manager for credentials.
