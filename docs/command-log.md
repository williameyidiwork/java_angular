# Command Log

This log explains the commands used while building the project. The goal is to make the terminal workflow understandable enough that it can be discussed in an interview, not just copied.

## Step 0: Project Charter And Architecture

### Read The Original Project Idea

```bash
sed -n '1,220p' '/Users/williameyidi/.codex/attachments/1dacc540-ff41-4e88-b347-7ca3b78bc1ac/pasted-text.txt'
```

Why:

- Reads the pasted project proposal without opening an editor.
- `sed -n '1,220p'` prints only the first 220 lines, which keeps terminal output controlled.

Result:

- Confirmed the project scope: Java, Spring Boot, Angular, PostgreSQL, Kafka, Docker, CI/CD, security, ETL, observability, and cloud deployment.

Interview language:

> I started by reading the requirements carefully so the architecture and roadmap matched the actual project goals.

### Check The Current Directory

```bash
pwd
```

Why:

- Confirms the active workspace path before creating or editing files.

Result:

- Confirmed the workspace is `/Users/williameyidi/Documents/java and angular`.

Interview language:

> Before changing files, I confirm the working directory so I do not accidentally modify the wrong project.

### Look For Existing Files

```bash
rg --files -g '!*node_modules*' -g '!*.class' -g '!target' -g '!dist' -g '!build' | sed -n '1,120p'
```

Why:

- `rg --files` quickly lists repository files.
- The `-g` filters skip generated folders and build artifacts.
- `sed -n '1,120p'` keeps the output small.

Result:

- No application files were present yet.

Interview language:

> I inspected the repository first so I could decide whether to extend an existing codebase or start with a clean scaffold.

### Check Git Status

```bash
git status --short
```

Why:

- Shows whether the working tree has modified, staged, or untracked files.
- The `--short` option is easier to scan than the full status output.

Result:

- The repository started clean.

Interview language:

> I check Git status before making changes so I know whether there are existing user changes I need to protect.

### Inspect The Directory

```bash
ls -la
```

Why:

- Lists visible and hidden files.
- Confirms whether `.git` exists and whether the repository has source files.

Result:

- The directory contained only `.git`.

Interview language:

> I verified that this was an empty repository with Git already initialized.

### Confirm The Git Repository Root

```bash
git rev-parse --show-toplevel
```

Why:

- Confirms the top-level Git repository directory.
- Useful when working inside nested folders.

Result:

- Confirmed the repository root is `/Users/williameyidi/Documents/java and angular`.

Interview language:

> I confirmed the Git root so new files would be created at the correct project level.

### Check The Current Branch

```bash
git branch --show-current
```

Why:

- Shows which branch is active before beginning a checkpoint.

Result:

- Confirmed the current branch is `main`.

Interview language:

> I checked the branch before starting because commits should land on the intended branch.

### Create The Documentation Folder

```bash
mkdir -p docs
```

Why:

- Creates a `docs` directory for architecture, roadmap, interview notes, and command notes.
- The `-p` option avoids an error if the folder already exists.

Result:

- Created the documentation folder.

Interview language:

> I separated project documentation into a docs folder so architecture and learning notes stay organized.

### Add The Initial Markdown Files

Files created:

- `README.md`
- `docs/architecture.md`
- `docs/interview-notes.md`
- `docs/roadmap.md`

Why:

- Establishes the project charter, architecture, interview vocabulary, and incremental roadmap before code.

Result:

- Added the Step 0 project foundation.

Interview language:

> I started with documentation because it clarifies the business problem, technology choices, and implementation sequence before writing code.

### Review The Markdown Files

```bash
sed -n '1,220p' README.md
sed -n '1,260p' docs/architecture.md
sed -n '1,240p' docs/interview-notes.md
sed -n '1,260p' docs/roadmap.md
```

Why:

- Reads the generated files back through the terminal.
- Confirms the content is present and readable.

Result:

- Verified the first documentation set.

Interview language:

> After writing files, I read them back to catch obvious mistakes before committing.

### Add A Git Ignore File

File created:

- `.gitignore`

Why:

- Keeps local generated files out of Git.
- Covers common Java, Maven, Angular, Node, IDE, macOS, environment, and Docker artifacts.

Result:

- Added repository hygiene before scaffolding backend or frontend projects.

Interview language:

> I add a `.gitignore` early so build outputs, dependencies, IDE files, and secrets do not accidentally get committed.

### Check For Whitespace Problems

```bash
git diff --check
```

Why:

- Detects whitespace errors before staging or committing.

Result:

- Passed with no whitespace problems.

Interview language:

> I run a lightweight diff check before committing so simple formatting mistakes do not enter the repository.

### List Created Files

```bash
rg --files
```

Why:

- Confirms which project files currently exist.

Result:

- Confirmed the new documentation files.

### Try To Stage The Checkpoint

```bash
git add .gitignore README.md docs/architecture.md docs/command-log.md docs/interview-notes.md docs/roadmap.md
```

Why:

- Stages the Step 0 files for the first commit.

Result:

- The command failed because the sandbox could not create `.git/index.lock`.

What that means:

- Editing normal project files is allowed, but writing into `.git` requires approval in this environment.

Interview language:

> Staging files modifies Git metadata, so in a restricted environment it may require elevated permission.

### Request Approval For Git Staging

```bash
git add .gitignore README.md docs/architecture.md docs/command-log.md docs/interview-notes.md docs/roadmap.md
```

Why:

- Retried the same staging command with approval requested because committing requires Git metadata writes.

Result:

- The approval request was rejected, so the files remain uncommitted for now.

Interview language:

> When a tool action needs elevated permission, I do not work around it. I request approval and continue only if it is granted.

### Add Command Notes After Feedback

Files updated:

- `README.md`
- `docs/command-log.md`

Why:

- The project should teach the terminal workflow, not only the final architecture.
- The README now links to this command log.

Result:

- Added command-by-command notes explaining what was run, why it was run, and how to explain the action professionally.

Interview language:

> I keep a lightweight command log while learning so I can explain the engineering workflow, not just the final code.

### Recheck The Current Changes

```bash
git diff --check
git status --short
```

Why:

- `git diff --check` confirms the updated markdown does not contain whitespace issues.
- `git status --short` confirms which files are still waiting to be committed.

Result:

- `git diff --check` passed.
- Git status showed `.gitignore`, `README.md`, and `docs/` as untracked.

Interview language:

> I rechecked the working tree after updating documentation so the checkpoint was ready for staging.

### Commit The Step 0 Checkpoint

These commands finish the Step 0 checkpoint:

```bash
git add .gitignore README.md docs/architecture.md docs/command-log.md docs/interview-notes.md docs/roadmap.md
git commit -m "chore: add project charter and architecture plan"
```

Why:

- `git add` stages the finished Step 0 files.
- `git commit` creates the permanent checkpoint before Step 1 begins.

Result:

- Created the first commit containing the project charter, architecture, interview notes, roadmap, command log, and `.gitignore`.

Interview language:

> I committed the documentation checkpoint before starting implementation so the project has a clean baseline.

### Update The Command Log After Commit

```bash
git add docs/command-log.md
git commit --amend --no-edit
```

Why:

- The first commit happened after the command log was written, so this note updates the log to reflect that the commit succeeded.
- `git commit --amend --no-edit` updates the existing Step 0 commit without creating a second documentation-only commit.

Expected result:

- The same Step 0 checkpoint remains as one commit, but now the command log accurately describes the completed workflow.

Interview language:

> If I need to fix the commit contents immediately after creating it, I amend the same local commit instead of creating noisy follow-up history.

## Current Checkpoint Status

Files created or updated:

- `.gitignore`
- `README.md`
- `docs/architecture.md`
- `docs/command-log.md`
- `docs/interview-notes.md`
- `docs/roadmap.md`

Git status:

- Step 0 documentation has been committed.
- The next checkpoint is Step 1: Spring Boot backend skeleton.

## Step 1 Preflight: Java And Maven Availability

### Check Whether Maven Is Installed

```bash
command -v mvn
```

Why:

- Checks whether the `mvn` command is available in the shell PATH.
- `command -v` is a portable way to ask the shell where a command comes from.

Result:

- No path was returned, so Maven is not currently available as a global command.

What that means:

- This is not a blocker.
- Professional Java projects often commit the Maven Wrapper, which lets developers run `./mvnw` without installing Maven globally.

Interview language:

> I do not require every developer to install Maven manually. I use the Maven Wrapper so the project can run with a consistent Maven version from the repository.

### Check Whether Java Is Installed

```bash
command -v java
command -v javac
```

Why:

- `java` runs compiled Java applications.
- `javac` compiles Java source code.
- A Spring Boot backend needs a JDK, not only a JRE.

Result:

- `java` was found at `/usr/bin/java`.
- `javac` was found at `/usr/bin/javac`.

Interview language:

> I checked both the runtime and compiler because backend development needs the full JDK.

### Check The Java Version

```bash
java -version
javac -version
```

Why:

- Confirms whether the installed JDK is compatible with the planned backend.
- The roadmap targets Java 21.

Result:

- Java runtime: Temurin OpenJDK 25.0.3 LTS.
- Java compiler: `javac 25.0.3`.

What that means:

- The installed JDK is newer than Java 21.
- The project can still target Java 21 by setting the Java version in the Maven build.

Interview language:

> My machine has a newer JDK installed, but the project build can still target Java 21 so the application has a predictable runtime baseline.

### Check The Git Working Tree

```bash
git status --short
```

Why:

- Confirms whether there are uncommitted changes before starting the next implementation checkpoint.

Result:

- The working tree was clean before this command-log update.

Interview language:

> I check Git status between checkpoints so every step starts from a known clean state.

## Step 1.1: Spring Boot Backend Scaffold

### Confirm The Starting State

```bash
git status --short
rg --files
java -version
```

Why:

- `git status --short` confirms there are no uncommitted changes before creating the backend.
- `rg --files` lists the current project files so we can see the repo shape.
- `java -version` confirms the Java runtime before generating a Java project.

Result:

- The Git working tree was clean.
- The repository contained the root README and docs.
- Java was available as Temurin OpenJDK 25.0.3.

Interview language:

> I start each implementation checkpoint by checking the repository state and local runtime so I know I am building from a clean baseline.

### Try To Download The Spring Boot Starter

```bash
curl -sS --get https://start.spring.io/starter.zip --data-urlencode type=maven-project --data-urlencode language=java --data-urlencode javaVersion=21 --data-urlencode dependencies=web,actuator,validation --data-urlencode groupId=com.example --data-urlencode artifactId=governance-platform --data-urlencode name=governance-platform --data-urlencode description=Enterprise data governance backend --data-urlencode packageName=com.example.governance --data-urlencode packaging=jar -o /tmp/governance-platform-backend.zip
```

Why:

- Downloads a standard Spring Boot project from the official Spring Initializr service.
- Requests a Maven project, Java 21 target, Spring Web MVC, Actuator, and Validation.
- Writes the generated zip to `/tmp` before extracting it into the repository.

Result:

- The command failed because the description value contained spaces and was not quoted.
- The shell treated `data`, `governance`, and `backend` as separate hostnames.

Interview language:

> When a shell command has an argument with spaces, I quote that argument so the shell passes it as one value.

### Retry The Download With Correct Quoting

```bash
curl -sS --get https://start.spring.io/starter.zip --data-urlencode type=maven-project --data-urlencode language=java --data-urlencode javaVersion=21 --data-urlencode dependencies=web,actuator,validation --data-urlencode groupId=com.example --data-urlencode artifactId=governance-platform --data-urlencode name=governance-platform --data-urlencode 'description=Enterprise data governance backend' --data-urlencode packageName=com.example.governance --data-urlencode packaging=jar -o /tmp/governance-platform-backend.zip
```

Why:

- Retries the same official Spring Initializr download with the description argument correctly quoted.

Result:

- Downloaded the starter zip successfully.

Interview language:

> I generated the project from Spring Initializr so the backend starts with the standard Spring Boot layout and a committed Maven Wrapper.

### Inspect The Downloaded Archive

```bash
unzip -l /tmp/governance-platform-backend.zip | sed -n '1,120p'
ls -lh /tmp/governance-platform-backend.zip
```

Why:

- `unzip -l` lists the zip contents before extraction.
- `sed -n '1,120p'` keeps the terminal output bounded.
- `ls -lh` confirms the archive exists and shows a human-readable size.

Result:

- Confirmed the zip contains `pom.xml`, `mvnw`, `mvnw.cmd`, source folders, test folders, `application.properties`, and Maven Wrapper properties.
- The archive size was about 15 KB.

Interview language:

> Before extracting generated code into a repo, I inspect the archive so I understand what files will be introduced.

### Extract The Backend Project

```bash
mkdir -p backend
unzip -q /tmp/governance-platform-backend.zip -d backend
```

Why:

- Creates a `backend` directory for the Spring Boot application.
- Extracts the starter project there so the repository can later also contain a separate Angular frontend.

Result:

- Created the first backend scaffold under `backend/`.

Interview language:

> I keep backend and frontend code in separate top-level folders so each application has its own build, dependencies, and runtime concerns.

### Inspect The Generated Backend Files

```bash
rg --files backend | sed -n '1,160p'
sed -n '1,240p' backend/pom.xml
sed -n '1,160p' backend/src/main/java/com/example/governance/GovernancePlatformApplication.java
sed -n '1,160p' backend/src/test/java/com/example/governance/GovernancePlatformApplicationTests.java
```

Why:

- Lists the generated backend files.
- Reads the Maven build configuration.
- Reads the Spring Boot application entry point.
- Reads the generated starter test.

Result:

- Spring Initializr generated a Maven project using Spring Boot 4.1.0.
- The Maven build targets Java 21 through `<java.version>21</java.version>`.
- The backend package is `com.example.governance`.
- The starter test uses `@SpringBootTest` and verifies that the application context loads.

Interview language:

> I inspected the generated project instead of treating scaffolding as a black box. The key files are the Maven `pom.xml`, the application entry point, and the starter test.

### Inspect Maven Wrapper And Configuration

```bash
sed -n '1,120p' backend/.mvn/wrapper/maven-wrapper.properties
ls -l backend/mvnw
sed -n '1,120p' backend/src/main/resources/application.properties
sed -n '1,160p' backend/.gitignore
```

Why:

- Checks how the Maven Wrapper is configured.
- Confirms `mvnw` is executable.
- Reads the initial Spring application configuration.
- Reviews backend-specific ignored files.

Result:

- The Maven Wrapper uses wrapper version 3.3.4.
- It downloads Maven 3.9.16 using `distributionType=only-script`.
- `backend/mvnw` is executable.
- The application name is `governance-platform`.
- The backend `.gitignore` excludes generated build and IDE files.

Interview language:

> The Maven Wrapper lets the project run without a globally installed Maven command, which makes onboarding and CI more predictable.

### Run The First Backend Test

```bash
./mvnw test
```

Why:

- Downloads Maven and project dependencies if needed.
- Compiles the application.
- Runs the generated Spring Boot context-load test.

Result:

- Build succeeded.
- Tests run: 1.
- Failures: 0.
- Errors: 0.
- The logs showed Spring Boot 4.1.0 starting under Java 25.0.3 while compiling with Java release 21.
- The test output included Mockito Java-agent warnings from the current JDK, but they did not fail the build.

Interview language:

> The first verification is a context-load test. It proves Spring can start the application with the current dependencies and configuration.

### Check Git Status After Scaffolding

```bash
git status --short
```

Why:

- Shows what changed before staging the scaffold.

Result:

- Git showed `backend/` as an untracked directory.

Interview language:

> I check the working tree before staging so I can confirm the checkpoint contains only the intended files.

### Final Checks Before Commit

```bash
git diff --check
git status --short
git status --short --ignored backend
```

Why:

- `git diff --check` catches whitespace issues before commit.
- `git status --short` shows the files that are modified or untracked.
- `git status --short --ignored backend` confirms generated backend files like build output are ignored.

Result:

- `git diff --check` passed.
- Git showed `docs/command-log.md` modified and `backend/` untracked.
- Git showed `backend/HELP.md` and `backend/target/` as ignored.

What that means:

- The backend scaffold is ready to stage.
- The generated build output will not be committed.

Interview language:

> Before committing, I verify both what will be tracked and what will stay ignored so the checkpoint contains source files, not generated output.

### Stage The Phase 1.1 Files

```bash
git add backend docs/command-log.md
```

Why:

- Stages the backend scaffold and the updated command log.
- `backend/HELP.md` and `backend/target/` stay ignored because of the backend `.gitignore`.

Result:

- Files were staged successfully.
- Git warned that `backend/mvnw.cmd` may use CRLF line endings when Git touches it. That is normal for the Windows Maven wrapper script.

Interview language:

> I stage only the intended source files and documentation, while generated files remain ignored.

### Inspect The Staged Files

```bash
git diff --cached --name-only
git status --short
```

Why:

- `git diff --cached --name-only` lists exactly what will go into the commit.
- `git status --short` shows the staged state in compact form.

Result:

- Confirmed the staged files are the backend wrapper, Maven build file, Spring Boot source files, application config, starter test, and `docs/command-log.md`.

Interview language:

> I inspect staged files before committing because a commit should represent one clear checkpoint, not accidental local output.

### Commit The Phase 1.1 Checkpoint

```bash
git add docs/command-log.md
git diff --cached --check
git status --short
git commit -m "chore: scaffold Spring Boot backend"
```

Why:

- The first `git add` restages the command log after documenting the staging commands.
- `git diff --cached --check` verifies staged files do not contain whitespace issues.
- `git status --short` confirms the staged checkpoint one last time.
- The commit records the Spring Boot backend scaffold as its own small checkpoint.

Result:

- The staged whitespace check passed.
- The staged checkpoint contained the backend scaffold and `docs/command-log.md`.
- The commit records the backend skeleton and command notes for this micro-step.

Interview language:

> I committed the backend scaffold separately so later business features can be reviewed independently from project setup.

## Step 1.2: First API Endpoint

### Confirm The Starting State

```bash
git status --short
find backend/src -type f | sort
sed -n '1,240p' backend/pom.xml
sed -n '1,160p' backend/src/main/java/com/example/governance/GovernancePlatformApplication.java
```

Why:

- `git status --short` confirms the working tree is clean before the next small checkpoint.
- `find backend/src -type f | sort` lists backend source files using a built-in command that works even when `rg` is not installed.
- Reading `pom.xml` confirms the current dependencies.
- Reading the main application class confirms the base package for Spring component scanning.

Result:

- The working tree was clean.
- The backend had only the generated application class, config file, and starter test.
- The project already had Spring Web MVC, Actuator, Validation, and their test dependencies.

Interview language:

> Before adding the first endpoint, I confirmed the package structure and dependencies so the new controller fits the existing Spring Boot application.

### Create API Package Folders

```bash
mkdir -p backend/src/main/java/com/example/governance/api backend/src/test/java/com/example/governance/api
```

Why:

- Creates a production `api` package for controller classes.
- Creates a matching test package for endpoint tests.
- `mkdir -p` creates parent folders if needed and does not fail if they already exist.

Result:

- Created folders for the first API endpoint and its test.

Interview language:

> I put controller code in an `api` package to separate HTTP/API concerns from the application bootstrap class.

### Add The Info Endpoint

Files created:

- `backend/src/main/java/com/example/governance/api/ApplicationInfoController.java`
- `backend/src/main/java/com/example/governance/api/ApplicationInfoResponse.java`

Why:

- `ApplicationInfoController` exposes a read-only HTTP route: `/api/v1/info`.
- `ApplicationInfoResponse` defines the JSON response shape using a Java record.
- The `/api/v1` path introduces basic API versioning from the beginning.

Result:

- Added a controller that returns:

```json
{
  "name": "governance-platform",
  "description": "Enterprise data governance backend",
  "apiVersion": "v1",
  "status": "UP"
}
```

Interview language:

> A controller maps HTTP requests to backend behavior. I used a response DTO so the API contract is explicit instead of returning an unstructured map.

### Add The Endpoint Test

File created:

- `backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java`

Why:

- Tests the endpoint through Spring's MVC test support.
- Verifies the HTTP status and JSON fields.

Result:

- Added a test for `GET /api/v1/info`.

Interview language:

> I test the endpoint at the HTTP layer so I can verify both routing and JSON serialization, not just Java method behavior.

### Review The New Files

```bash
sed -n '1,160p' backend/src/main/java/com/example/governance/api/ApplicationInfoController.java
sed -n '1,160p' backend/src/main/java/com/example/governance/api/ApplicationInfoResponse.java
sed -n '1,180p' backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
```

Why:

- Reads the files back after editing.
- Confirms the controller, response record, and test are small and understandable.

Result:

- Verified the new files before running tests.

Interview language:

> After editing, I read back the files so I catch simple mistakes before running the full build.

### Run The Test Suite

```bash
./mvnw test
```

Why:

- Compiles production and test code.
- Runs the Spring Boot context test and the new endpoint test.

Result:

- The first run failed during test compilation.
- The import `org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc` did not exist in Spring Boot 4.1.0.

What that means:

- Many Spring Boot 3 examples use the older package path.
- Spring Boot 4 has moved the MockMvc test auto-configuration classes.

Interview language:

> The build failed at test compilation, so I checked the dependency contents instead of guessing. The issue was an import path mismatch with Spring Boot 4.

### Inspect The Downloaded Spring Boot Test Jars

```bash
find /Users/williameyidi/.m2/repository/org/springframework/boot -name '*webmvc-test*.jar' -print
find /Users/williameyidi/.m2/repository/org/springframework/boot -name '*test*.jar' -maxdepth 6 -print | sed -n '1,120p'
jar tf /Users/williameyidi/.m2/repository/org/springframework/boot/spring-boot-test-autoconfigure/4.1.0/spring-boot-test-autoconfigure-4.1.0.jar | grep -E 'AutoConfigure.*Mock|WebMvcTest|servlet' | sed -n '1,160p'
jar tf /Users/williameyidi/.m2/repository/org/springframework/boot/spring-boot-webmvc-test/4.1.0/spring-boot-webmvc-test-4.1.0.jar | grep -E 'AutoConfigure|WebMvcTest|MockMvc|Test' | sed -n '1,160p'
```

Why:

- Looks inside the locally downloaded Maven dependencies.
- Finds the actual package containing `AutoConfigureMockMvc`.

Result:

- Found `AutoConfigureMockMvc` in:

```text
org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
```

Interview language:

> When documentation or examples do not match the installed version, I inspect the actual dependency jars to verify the available classes.

### Fix The Test Import

File updated:

- `backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java`

Change:

```java
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
```

Why:

- Updates the test to use the Spring Boot 4 package.

Result:

- The test compiled with the current Spring Boot version.

Interview language:

> I updated the test to match the framework version we are actually using.

### Rerun The Test Suite

```bash
./mvnw test
```

Why:

- Verifies the import fix.
- Confirms the endpoint works.

Result:

- Build succeeded.
- Tests run: 2.
- Failures: 0.
- Errors: 0.

Interview language:

> After fixing the framework import, I reran the full backend test suite and confirmed both the application context and the new endpoint test passed.

### Check The Working Tree After The Endpoint

```bash
git status --short
find backend/src/main/java backend/src/test/java -type f | sort
```

Why:

- Shows the files changed by this checkpoint.
- Lists the source files now present in the backend.

Result:

- Git showed the new production `api` package and new test `api` package as untracked.
- The backend now contains the application class, first API controller, response record, starter context test, and endpoint test.

Interview language:

> Before committing, I checked that this checkpoint contains only the first API endpoint and its test.

### Final Checks Before Staging

```bash
git diff --check
git status --short
git status --short --ignored backend
```

Why:

- `git diff --check` verifies there are no whitespace errors.
- `git status --short` shows the modified and untracked source files.
- `git status --short --ignored backend` confirms generated backend files remain ignored.

Result:

- `git diff --check` passed.
- Git showed `docs/command-log.md` as modified.
- Git showed the new API production and test packages as untracked.
- Git showed ignored local files such as `backend/target/`, Eclipse metadata, `HELP.md`, and `.DS_Store`.

Interview language:

> I verified the commit boundary before staging so generated build output and IDE metadata stay out of source control.

### Stage The Phase 1.2 Files

```bash
git add backend/src/main/java/com/example/governance/api backend/src/test/java/com/example/governance/api docs/command-log.md
```

Why:

- Stages only the first API endpoint, its test, and the command log.

Expected result:

- The commit will contain a small, reviewable endpoint checkpoint.

Interview language:

> I stage the exact directories for this feature so the commit stays focused.

### Inspect The Staged Files

```bash
git diff --cached --name-only
git diff --cached --check
git status --short
```

Why:

- `git diff --cached --name-only` lists exactly what will be committed.
- `git diff --cached --check` checks staged files for whitespace issues.
- `git status --short` confirms the staged state.

Result:

- Staged files were:
  - `ApplicationInfoController.java`
  - `ApplicationInfoResponse.java`
  - `ApplicationInfoControllerTests.java`
  - `docs/command-log.md`
- The staged whitespace check passed.

Interview language:

> I inspect staged files before committing so I can explain exactly what the checkpoint contains.

### Commit The Phase 1.2 Checkpoint

```bash
git add docs/command-log.md
git commit -m "feat: add application info endpoint"
```

Why:

- Restages the command log after documenting the staged-file inspection.
- Commits the first API endpoint as a focused feature checkpoint.

Expected result:

- A commit containing the `/api/v1/info` endpoint, its response DTO, its endpoint test, and the command notes.

Interview language:

> I committed the first endpoint separately because it proves the web layer works before adding database or security concerns.

## Step 1.2a: Practice Endpoint Tests

### Notice Existing Test Changes

```bash
git status --short
git diff -- backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
sed -n '1,180p' backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
```

Why:

- Checks whether the working tree is clean before starting Phase 1.3.
- Inspects the existing test changes instead of overwriting them.
- Reads the full test file to understand the intent.

Result:

- Found two additional endpoint tests in `ApplicationInfoControllerTests.java`.
- The tests were related to the current endpoint, so they were kept and cleaned up.

Interview language:

> When I find existing uncommitted work, I inspect it first and preserve the intent instead of blindly overwriting it.

### Tidy The Additional Tests

File updated:

- `backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java`

Why:

- Fixed the test method typo from `Infor` to `Info`.
- Removed extra blank lines and spacing.
- Aligned the MockMvc assertion chains.
- Put the `status().isOk()` assertion before JSON assertions in the status-focused test.

Result:

- Added two clean test methods:

```java
getApplicationInfoReturnsStatusOk()
getApplicationInfoReturnsDescription()
```

Interview language:

> Test names should read like behavior descriptions. Clean naming makes test reports easier to understand.

### Run The Backend Tests

```bash
./mvnw test
```

Why:

- Verifies that the extra endpoint tests compile and pass.

Result:

- Build succeeded.
- Tests run: 4.
- Failures: 0.
- Errors: 0.

Interview language:

> After changing tests, I reran the backend suite to confirm the behavior is still green.

### Review The Final Test Diff

```bash
git diff -- backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
```

Why:

- Confirms the checkpoint only adds the two intended endpoint tests.

Result:

- The diff was focused on two test methods.

Interview language:

> I reviewed the diff before committing so I could keep the practice-test checkpoint small and explainable.

## Step 1.3: Runtime HTTP Verification

### Confirm The Starting State

```bash
git status --short
sed -n '1,80p' backend/src/main/resources/application.properties
```

Why:

- Confirms Phase 1.3 starts from a clean Git checkpoint.
- Reads the current application configuration before starting the server.

Result:

- The working tree was clean.
- The application name was configured as `governance-platform`.

Interview language:

> Before runtime verification, I confirmed the repo was clean and checked the active application configuration.

### Start The Backend Server

```bash
cd backend
./mvnw spring-boot:run
```

Why:

- Starts the Spring Boot application as a real local HTTP server.
- Uses the Maven Wrapper, so global Maven is not required.

Result:

- Maven ran the Spring Boot plugin.
- Tomcat started on port `8080`.
- Spring Boot started the application with context path `/`.
- Actuator exposed one endpoint beneath `/actuator`.

Important log lines:

```text
Tomcat started on port 8080 (http) with context path '/'
Started GovernancePlatformApplication
```

Interview language:

> `spring-boot:run` starts the application locally using the embedded Tomcat server, which lets me test real HTTP requests against localhost.

### Try Calling The Endpoint From The Sandboxed Shell

```bash
curl -i http://localhost:8080/api/v1/info
curl -sS http://localhost:8080/api/v1/info
```

Why:

- Calls the `/api/v1/info` endpoint over HTTP.
- `-i` includes HTTP response headers.
- `-sS` keeps output quiet but still shows errors.

Result:

- These first calls failed with:

```text
curl: (7) Failed to connect to localhost port 8080
```

What that means:

- The server was running in an elevated command context.
- The sandboxed command context could not reach that local process.
- This was an environment boundary issue, not a Spring Boot application failure.

Interview language:

> The first HTTP call failed because of the local execution environment, not because the application was down. I confirmed the server process was still running before retrying from the matching context.

### Confirm The Server Was Still Running

The running server process was checked through the active terminal session.

Result:

- The Spring Boot process was still running.

Interview language:

> When a request fails, I first check whether the service is actually running before changing application code.

### Call The Info Endpoint Successfully

```bash
curl -i http://localhost:8080/api/v1/info
```

Why:

- Sends a real HTTP GET request to the running backend.
- Includes response headers and body.

Result:

```text
HTTP/1.1 200
Content-Type: application/json
```

Response body:

```json
{"name":"governance-platform","description":"Enterprise data governance backend","apiVersion":"v1","status":"UP"}
```

Interview language:

> I verified the endpoint through a real HTTP request, which proves the route works through embedded Tomcat, Spring MVC routing, controller execution, and JSON serialization.

### Call The Actuator Health Endpoint

```bash
curl -i http://localhost:8080/actuator/health
```

Why:

- Verifies Spring Boot Actuator health reporting.
- Health endpoints are used by deployment platforms, load balancers, and monitoring systems.

Result:

```text
HTTP/1.1 200
Content-Type: application/vnd.spring-boot.actuator.v3+json
```

Response body:

```json
{"groups":["liveness","readiness"],"status":"UP"}
```

Interview language:

> Actuator health gives an operational signal that the application is running and can participate in liveness/readiness checks.

### Stop The Backend Server

```text
Ctrl+C
```

Why:

- Stops the local development server after verification.
- Prevents a background process from continuing to use port `8080`.

Result:

- Spring Boot performed a graceful shutdown.
- Maven reported `BUILD SUCCESS`.

Important log lines:

```text
Commencing graceful shutdown. Waiting for active requests to complete
Graceful shutdown complete
BUILD SUCCESS
```

Interview language:

> I stopped the local server cleanly after testing, which freed the port and confirmed the application shut down gracefully.

### Where To Override The Spring Boot Port

File:

- `backend/src/main/resources/application.properties`

Current content:

```properties
spring.application.name=governance-platform
```

Why:

- Spring Boot uses port `8080` by default.
- The default can be changed by setting `server.port`.

Example:

```properties
spring.application.name=governance-platform
server.port=8081
```

Result:

- The backend would start on `http://localhost:8081` instead of `http://localhost:8080`.

Temporary command-line override:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Why:

- Useful when port `8080` is already being used.
- Avoids changing committed configuration for a one-time local run.

Interview language:

> Spring Boot uses port 8080 by default. I can override it with `server.port` in `application.properties`, or pass `--server.port` as a runtime argument for temporary local runs.

## Step 2.1: Local PostgreSQL With Docker Compose

### Confirm The Starting State

```bash
git status --short
find . -maxdepth 3 -type f | sort | sed -n '1,160p'
docker info
```

Why:

- `git status --short` confirms the working tree is clean before adding infrastructure files.
- `find` lists current files using a built-in command.
- `docker info` checks whether Docker is installed and whether the Docker daemon is running.

Result:

- The working tree was clean.
- Docker CLI was installed.
- Docker Compose was available as a Docker plugin.
- Docker daemon was not running yet.

Important output:

```text
Cannot connect to the Docker daemon
```

Interview language:

> Before adding local infrastructure, I checked the repository state and verified whether Docker was available and running.

### Start Docker Desktop

```bash
open -a Docker
```

Why:

- Starts Docker Desktop on macOS so the Docker daemon becomes available.

Result:

- Docker Desktop opened successfully.

Interview language:

> Docker commands need the Docker daemon. On macOS, Docker Desktop provides that daemon.

### Add Docker Compose PostgreSQL Configuration

Files created:

- `compose.yaml`
- `.env.example`

Why:

- `compose.yaml` defines the local PostgreSQL service.
- `.env.example` documents configurable local database variables without committing a real `.env` file.
- The actual `.env` file is ignored by Git.

Compose service:

```yaml
postgres:
  image: postgres:17-alpine
  container_name: governance-platform-postgres
```

Important settings:

- `POSTGRES_DB`: creates the local database.
- `POSTGRES_USER`: creates the local database user.
- `POSTGRES_PASSWORD`: sets the local development password.
- `POSTGRES_PORT`: maps the host port to the container port.
- `postgres_data`: named Docker volume for persistent database files.
- `healthcheck`: uses `pg_isready` to report when PostgreSQL is ready.

Interview language:

> I use Docker Compose to make PostgreSQL repeatable locally. The database, user, password, port, health check, and storage volume are defined as code.

### Review The New Files

```bash
sed -n '1,180p' compose.yaml
sed -n '1,80p' .env.example
git status --short
```

Why:

- Reads the Compose and environment example files after editing.
- Confirms Git sees the new files as untracked before staging.

Result:

- Confirmed the PostgreSQL service definition.
- Confirmed default local values:

```text
POSTGRES_DB=governance
POSTGRES_USER=governance
POSTGRES_PASSWORD=governance
POSTGRES_PORT=5432
```

Interview language:

> After adding infrastructure configuration, I read it back before running it so I can catch simple mistakes early.

### Validate The Compose File

```bash
docker compose config
```

Why:

- Validates and expands the Compose file.
- Confirms environment-variable defaults resolve correctly.
- Does not start containers.

Result:

- Compose validation passed.
- Docker expanded the project name, network name, volume name, port mapping, environment variables, and health check.

Interview language:

> I validate Docker Compose configuration before starting containers so YAML and variable mistakes fail fast.

### Check Docker Readiness Again

```bash
docker info
```

Why:

- Confirms Docker Desktop is now running before starting the database.

Result:

- Docker daemon was available.
- Docker server reported version `29.4.3`.

Interview language:

> I rechecked Docker after opening Docker Desktop to confirm the daemon was ready.

### Start PostgreSQL

```bash
docker compose up -d postgres
```

Why:

- Starts only the PostgreSQL service.
- `-d` runs it in detached/background mode.

Result:

- Docker pulled the `postgres:17-alpine` image.
- Docker created the Compose network.
- Docker created the named volume.
- Docker created and started the `governance-platform-postgres` container.

Interview language:

> `docker compose up -d postgres` starts the database dependency in the background so the backend can later connect to a repeatable local PostgreSQL instance.

### Check Container Health

```bash
docker compose ps
docker compose exec postgres pg_isready -U governance -d governance
```

Why:

- `docker compose ps` shows container status, ports, and health.
- `pg_isready` asks PostgreSQL whether it can accept connections.

Result:

- Container status was `Up` and `healthy`.
- PostgreSQL reported:

```text
/var/run/postgresql:5432 - accepting connections
```

Interview language:

> I do not assume a started container means the database is ready. I check the health status and use `pg_isready` to verify PostgreSQL can accept connections.

### Verify Database And User

```bash
docker compose exec postgres psql -U governance -d governance -c 'select current_database(), current_user;'
```

Why:

- Runs a SQL query inside the PostgreSQL container.
- Confirms the configured database and user are active.

Result:

```text
current_database | current_user
------------------+--------------
governance        | governance
```

Interview language:

> I verified the database by running a real SQL query, not just checking that the container started.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops and removes the running container and network.
- Preserves the named Docker volume because `-v` was not used.

Result:

- Container stopped and was removed.
- Compose network was removed.
- The data volume was preserved.

Interview language:

> I used `docker compose down` to stop the local service cleanly while preserving the named data volume for future runs.

### Confirm Services Are Stopped

```bash
docker compose ps
git status --short
docker volume ls --filter name=governance-platform_postgres_data
```

Why:

- Confirms no Compose services are running.
- Checks which files still need to be committed.
- Confirms the named database volume still exists.

Result:

- `docker compose ps` showed no running services.
- Git showed `.env.example` and `compose.yaml` as untracked.
- The first direct `docker volume ls` call hit a Docker socket permission boundary.

Interview language:

> After stopping local services, I verify both runtime cleanup and source-control state.

### Check The Preserved Docker Volume

```bash
docker volume ls --filter name=governance-platform_postgres_data
```

Why:

- Confirms the named volume still exists after `docker compose down`.

Result:

```text
DRIVER    VOLUME NAME
local     governance-platform_postgres_data
```

Interview language:

> The container can be removed without losing local PostgreSQL files because database storage lives in a named Docker volume.

### Add README Usage Notes

File updated:

- `README.md`

Why:

- Adds the basic local PostgreSQL workflow where a developer is most likely to look first.
- Keeps the command log detailed while keeping the README practical.

README commands added:

```bash
docker compose up -d postgres
docker compose ps
docker compose exec postgres pg_isready -U governance -d governance
docker compose down
```

Interview language:

> I documented the common local database commands in the README so another developer can start, check, and stop the dependency without reading the full command log.

### Final Checks Before Commit

```bash
git diff --check
docker compose config
git status --short
sed -n '35,110p' README.md
```

Why:

- `git diff --check` confirms the new files and docs do not contain whitespace problems.
- `docker compose config` revalidates the Compose file after documentation changes.
- `git status --short` shows the exact files waiting to be committed.
- Reading the README section confirms the developer-facing instructions are clear.

Result:

- Whitespace check passed.
- Compose validation passed.
- Git showed:

```text
M README.md
M docs/command-log.md
?? .env.example
?? compose.yaml
```

Interview language:

> Before committing infrastructure configuration, I validated the Compose file again and checked that only the intended config and documentation files were changed.

### Stage The Phase 2.1 Files

```bash
git add compose.yaml .env.example README.md docs/command-log.md
```

Why:

- Stages the PostgreSQL Compose file.
- Stages the environment example.
- Stages README usage notes.
- Stages the command log.

Result:

- Staging succeeded.

Interview language:

> I staged only the local database platform files and related documentation so the commit stays focused.

### Inspect The Staged Files

```bash
git diff --cached --name-only
git diff --cached --check
git status --short
```

Why:

- `git diff --cached --name-only` lists exactly what will be committed.
- `git diff --cached --check` checks staged files for whitespace issues.
- `git status --short` confirms the staged state.

Result:

- Staged files were:
  - `.env.example`
  - `README.md`
  - `compose.yaml`
  - `docs/command-log.md`
- Staged whitespace check passed.

Interview language:

> I inspect staged files before committing so the checkpoint is easy to review and explain.

### Commit The Phase 2.1 Checkpoint

```bash
git add docs/command-log.md
git commit -m "chore: add local PostgreSQL compose service"
```

Why:

- Restages the command log after documenting the staged-file inspection.
- Commits the local PostgreSQL platform as its own infrastructure checkpoint.

Expected result:

- A commit containing the Docker Compose PostgreSQL service, environment example, README usage notes, and command trail.

Interview language:

> I committed the local database platform separately before wiring it into Spring Boot, which keeps infrastructure setup and application integration as two clear reviewable steps.

## Step 2.1a: Inspect Local PostgreSQL

### Check Repo And Docker State

```bash
git status --short
docker compose ps
docker info
```

Why:

- Checks whether there are uncommitted files before inspecting the database.
- Confirms whether any Compose services are already running.
- Confirms Docker Desktop and the Docker daemon are available.

Result:

- Docker was running.
- No Compose services were running.
- Git showed an untracked `.compose.yaml.swp` editor swap file.

Interview language:

> Before starting infrastructure, I check both source-control state and Docker runtime state so I know what environment I am working in.

### Start PostgreSQL

```bash
docker compose up -d postgres
```

Why:

- Starts the PostgreSQL service defined in `compose.yaml`.
- Runs it in detached mode so the terminal can be used for inspection commands.

Result:

- Docker created the Compose network.
- Docker created and started the `governance-platform-postgres` container.

Interview language:

> I started only the PostgreSQL service because this checkpoint is about inspecting the database, not running the full application stack.

### Check Service And Database Readiness

```bash
docker compose ps
docker compose exec postgres pg_isready -U governance -d governance
```

Why:

- `docker compose ps` shows whether the container is running and which port is published.
- `pg_isready` asks PostgreSQL whether it is accepting connections.

Result:

- The container was running on host port `5432`.
- PostgreSQL reported:

```text
/var/run/postgresql:5432 - accepting connections
```

Interview language:

> I verified readiness at the database level, not only at the container level.

### Confirm Current Database And User

```bash
docker compose exec postgres psql -U governance -d governance -c 'select current_database(), current_user;'
```

Why:

- Connects to PostgreSQL using the configured local user and database.
- Runs a small SQL query to prove the connection context.

Result:

```text
current_database | current_user
------------------+--------------
governance        | governance
```

Interview language:

> I confirmed that the configured database and user match the Compose settings before looking for application tables.

### List Tables With psql

```bash
docker compose exec postgres psql -U governance -d governance -c '\dt'
```

Why:

- `\dt` is a `psql` meta-command that lists tables in the current schema.

Result:

```text
Did not find any relations.
```

What that means:

- PostgreSQL is working.
- The `governance` database exists.
- There are no application tables yet because we have not added Flyway migrations or JPA schema creation.

Interview language:

> The database is healthy, but there are no application tables yet because schema management has not been introduced.

### List User Tables Through information_schema

```bash
docker compose exec postgres psql -U governance -d governance -c "select table_schema, table_name from information_schema.tables where table_schema not in ('pg_catalog', 'information_schema') order by table_schema, table_name;"
```

Why:

- Uses standard SQL to list user-created tables.
- Excludes PostgreSQL system schemas.

Result:

```text
table_schema | table_name
--------------+------------
(0 rows)
```

Interview language:

> I checked both the `psql` table listing and `information_schema` to confirm there are no user tables yet.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops and removes the PostgreSQL container and Compose network.
- Preserves the named PostgreSQL data volume.

Result:

- Container stopped and was removed.
- Network was removed.

Interview language:

> I stopped the local database after inspection so no background container continues using port 5432.

### Confirm Final State

```bash
docker compose ps
git status --short
```

Why:

- Confirms no Compose services are running.
- Checks source-control state after the inspection.

Result:

- No Compose services were running.
- Git showed the local editor swap file `.compose.yaml.swp`.

Follow-up:

- Added `*.swp` to `.gitignore` so editor swap files are ignored without deleting local files.

Interview language:

> I ignore editor swap files because they are local machine artifacts, not project source.

## Step 2.2: Connect Spring Boot To PostgreSQL

### Check Starting State

```bash
git status --short
docker compose ps
sed -n '1,220p' backend/pom.xml
sed -n '1,120p' backend/src/main/resources/application.properties
```

Why:

- Confirms the repo is clean before changing backend dependencies.
- Confirms no Compose services are running.
- Reads the current Maven dependencies and application config.

Result:

- Git was clean.
- No Compose services were running.
- Backend had web, actuator, validation, and test dependencies only.
- `application.properties` only contained `spring.application.name`.

Interview language:

> I checked the current backend build and config before adding database integration so the change stays focused.

### Confirm Current Spring Boot Database Dependencies

```bash
curl -sS --get https://start.spring.io/starter.zip --data-urlencode type=maven-project --data-urlencode language=java --data-urlencode javaVersion=21 --data-urlencode dependencies=data-jpa,postgresql,flyway --data-urlencode groupId=com.example --data-urlencode artifactId=dependency-check --data-urlencode name=dependency-check --data-urlencode packageName=com.example.check --data-urlencode packaging=jar -o /tmp/governance-platform-db-deps.zip
unzip -p /tmp/governance-platform-db-deps.zip pom.xml | sed -n '1,220p'
unzip -l /tmp/governance-platform-db-deps.zip | sed -n '1,80p'
```

Why:

- Uses Spring Initializr as the source of truth for current Spring Boot 4 dependency names.
- Saves the temporary project outside the repository.
- Inspects only the generated `pom.xml`.

Result:

- Confirmed these dependencies:

```xml
spring-boot-starter-data-jpa
spring-boot-starter-flyway
flyway-database-postgresql
postgresql
spring-boot-starter-data-jpa-test
spring-boot-starter-flyway-test
```

Interview language:

> I confirmed the dependency names from Spring Initializr instead of guessing, because Spring Boot 4 starter names differ from some older examples.

### Add Database Dependencies And Config

Files updated:

- `backend/pom.xml`
- `backend/src/main/resources/application.properties`

Files added:

- `backend/src/main/resources/db/migration/.gitkeep`
- `backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java`

Why:

- Adds Spring Data JPA for persistence support.
- Adds Flyway for schema migrations.
- Adds PostgreSQL JDBC driver for runtime database connections.
- Adds database test dependencies.
- Configures datasource URL, username, and password with environment-variable overrides.
- Sets `spring.jpa.hibernate.ddl-auto=validate` so Hibernate does not silently create tables.
- Sets `spring.jpa.open-in-view=false` to avoid keeping persistence sessions open during web rendering.
- Enables health details so local Actuator output shows database status.
- Adds an empty migration folder marker so the Flyway location exists before real migrations are added.
- Adds a JDBC test that verifies Spring can query the configured database.

Configuration added:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/governance}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:governance}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:governance}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false
management.endpoint.health.show-details=always
```

Interview language:

> I configured the backend to use PostgreSQL through Spring Boot datasource properties, but kept schema creation under Flyway control instead of allowing Hibernate to auto-create tables.

### Review The Backend Changes

```bash
sed -n '1,260p' backend/pom.xml
sed -n '1,120p' backend/src/main/resources/application.properties
sed -n '1,180p' backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java
find backend/src/main/resources -type f | sort
```

Why:

- Reads the changed Maven file.
- Reads the datasource configuration.
- Reads the new database connectivity test.
- Confirms the migration folder marker exists.

Result:

- Confirmed the dependencies, config, `.gitkeep`, and database test.

Interview language:

> After editing backend configuration, I read the files back before running tests so simple configuration mistakes are easier to catch.

### Start PostgreSQL For Backend Tests

```bash
docker compose up -d postgres
docker compose ps
docker compose exec postgres pg_isready -U governance -d governance
```

Why:

- Starts the local PostgreSQL dependency.
- Checks container status.
- Checks database readiness.

Result:

- PostgreSQL started.
- `pg_isready` reported it was accepting connections.

Interview language:

> Once the Spring context needs a datasource, the local database must be running before integration-style tests can pass.

### Run Backend Tests Against PostgreSQL

```bash
cd backend
./mvnw test
```

Why:

- Downloads new database dependencies.
- Compiles the backend.
- Starts the Spring test context with a real PostgreSQL datasource.
- Runs the explicit JDBC connectivity test.

Result:

- Build succeeded.
- Tests run: 5.
- Failures: 0.
- Errors: 0.

Important runtime signals:

```text
HikariPool - Start completed
Database: jdbc:postgresql://localhost:5432/governance
PostgreSQL 17.10
Found 0 JPA repository interfaces
Successfully validated 0 migrations
No migrations found
```

What that means:

- Spring Boot connected through HikariCP.
- Flyway reached PostgreSQL.
- Hibernate initialized against PostgreSQL.
- There are no repositories/entities yet, which is expected.
- There are no business migrations yet, which is expected.

Interview language:

> The test run proved the backend can start with a real PostgreSQL datasource. Hikari opened a connection pool, Flyway checked migrations, Hibernate initialized JPA, and the JDBC test queried the configured database.

### Inspect Tables After Flyway Runs

```bash
docker compose ps
docker compose exec postgres psql -U governance -d governance -c '\dt'
docker compose exec postgres psql -U governance -d governance -c "select table_name from information_schema.tables where table_schema = 'public' order by table_name;"
```

Why:

- Checks container health.
- Lists tables after the application test run.
- Confirms whether Flyway created any metadata.

Result:

- PostgreSQL container was healthy.
- One table existed:

```text
flyway_schema_history
```

What that means:

- Flyway created its schema history table.
- No business tables exist yet.

Interview language:

> `flyway_schema_history` is Flyway's bookkeeping table. It records migration history; it is not an application business table.

### Try To Run The Backend Normally

```bash
cd backend
./mvnw spring-boot:run
```

Why:

- Starts the backend as a real local server with the new database configuration.

Result:

- The app connected to PostgreSQL successfully.
- Startup failed because port `8080` was already in use.

Important output:

```text
Web server failed to start. Port 8080 was already in use.
```

Interview language:

> The database integration worked, but the web server could not bind to port 8080 because another process was already using it.

### Run The Backend On A Temporary Port

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Why:

- Runs the backend on port `8081` without changing committed configuration.
- Uses the temporary Spring Boot runtime argument learned earlier.

Result:

- Tomcat started on port `8081`.
- Hikari connected to PostgreSQL.
- Flyway checked migrations.
- Hibernate initialized JPA.
- The application started successfully.

Interview language:

> I used a runtime port override to avoid changing source config for a local port conflict.

### Verify Runtime Database Health

```bash
curl -i http://localhost:8081/actuator/health
curl -i http://localhost:8081/api/v1/info
```

Why:

- Calls the running application over real HTTP.
- Verifies Actuator reports database health.
- Confirms the existing info endpoint still works after adding database configuration.

Result:

- `/actuator/health` returned `HTTP/1.1 200`.
- Health output included:

```json
"db":{"details":{"database":"PostgreSQL","validationQuery":"isValid()"},"status":"UP"}
```

- `/api/v1/info` returned `HTTP/1.1 200` and the expected JSON response.

Interview language:

> Runtime health showed `db.status=UP`, which proves the running service can reach PostgreSQL through its configured datasource.

### Stop Runtime Services

```text
Ctrl+C
docker compose down
```

Why:

- Stops the Spring Boot app cleanly.
- Stops and removes the PostgreSQL container and Compose network.
- Preserves the named PostgreSQL volume.

Result:

- Spring Boot performed graceful shutdown.
- JPA EntityManagerFactory closed.
- Hikari connection pool shut down.
- PostgreSQL container and network were removed.

Interview language:

> I stopped both the app and database after verification so no background service kept using local ports.

### Final Runtime And Source Check

```bash
docker compose ps
git status --short
git diff -- backend/pom.xml backend/src/main/resources/application.properties backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java backend/src/main/resources/db/migration/.gitkeep | sed -n '1,260p'
```

Why:

- Confirms no Compose services are running.
- Shows the source files changed by this checkpoint.
- Reviews the diff before documentation and commit.

Result:

- No Compose services were running.
- Git showed backend dependency/config/test changes.

Interview language:

> I checked both runtime cleanup and source diff before committing the database integration checkpoint.

### Add README Backend Instructions

File updated:

- `README.md`

Why:

- Backend tests now require PostgreSQL to be running.
- README should tell developers how to start PostgreSQL, run tests, and use a temporary port if `8080` is busy.

Commands documented:

```bash
docker compose up -d postgres
cd backend
./mvnw test
./mvnw spring-boot:run
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Interview language:

> I updated developer documentation when the test workflow changed, because backend tests now depend on the local PostgreSQL service.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- `git diff --check` verifies whitespace.
- `docker compose ps` confirms no runtime services are still running.
- `git status --short` lists changed and untracked files.
- `git diff --stat` gives a compact summary of the checkpoint.

Result:

- Whitespace check passed.
- No Compose services were running.
- Git showed README, backend config/dependency changes, command log changes, and new database resource/test folders.

Interview language:

> I verified both runtime cleanup and source-control state before staging the database integration commit.

### Stage The Phase 2.2 Files

```bash
git add README.md backend/pom.xml backend/src/main/resources/application.properties backend/src/main/resources/db/migration/.gitkeep backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java docs/command-log.md
```

Why:

- Stages the exact database integration files and documentation.

Expected result:

- A focused commit containing the Spring Boot PostgreSQL connection work.

Interview language:

> I stage the exact files for this integration checkpoint so generated build output stays out of the commit.

### Inspect The Staged Files

```bash
git diff --cached --name-only
git diff --cached --check
git status --short
```

Why:

- Lists exactly what will be committed.
- Checks staged files for whitespace issues.
- Confirms the staged state.

Result:

- Staged files were:
  - `README.md`
  - `backend/pom.xml`
  - `backend/src/main/resources/application.properties`
  - `backend/src/main/resources/db/migration/.gitkeep`
  - `backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.

Interview language:

> I inspect the staged files before committing so the database integration checkpoint is reviewable and explainable.

### Commit The Phase 2.2 Checkpoint

```bash
git add docs/command-log.md
git commit -m "feat: connect backend to PostgreSQL"
```

Why:

- Restages the command log after documenting the staged-file inspection.
- Commits the Spring Boot PostgreSQL integration as a focused checkpoint.

Expected result:

- A commit containing the backend database dependencies, datasource config, migration folder marker, database connectivity test, README notes, and command trail.

Interview language:

> I committed the database connection separately before adding business tables, which keeps connectivity concerns distinct from schema design.

## Step 2.2a: Refactor Database Test To JUnit Assertions

### Inspect The Current Database Test

```bash
git status --short
sed -n '1,180p' backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java
docker compose ps
```

Why:

- Confirms the repo is clean before changing the test.
- Reads the current database connection test.
- Confirms PostgreSQL is not already running.

Result:

- Git was clean.
- The database test already used JUnit's `@Test`.
- The assertions were written with AssertJ.
- No Compose services were running.

Interview language:

> I inspected the test before changing it so the refactor only changed assertion style, not test behavior.

### Replace AssertJ Assertions With JUnit Assertions

File updated:

- `backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java`

Change:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

Assertions:

```java
assertEquals("governance", result.get("database_name"));
assertEquals("governance", result.get("user_name"));
```

Why:

- Makes the test visibly use JUnit assertions.
- Keeps the same SQL query and real database verification.

Result:

- The database test now uses JUnit `assertEquals`.

Interview language:

> The test was already a JUnit test because it used JUnit's `@Test`; I changed the assertions to JUnit assertions so the whole test reads as plain JUnit.

### Why This Test Does Not Mock The Database

Reason:

- This is an integration test, not a pure unit test.
- Its job is to prove the real Spring datasource configuration can reach real PostgreSQL.

What the real database test verifies:

- The datasource URL is correct.
- The username and password work.
- The PostgreSQL JDBC driver is present.
- HikariCP can open a connection.
- PostgreSQL is accepting connections.
- Spring can inject and use `JdbcTemplate`.
- A real SQL query returns the expected database and user.

What a mock would not verify:

- Whether PostgreSQL is actually running.
- Whether the JDBC URL is correct.
- Whether credentials are correct.
- Whether the PostgreSQL driver is on the classpath.
- Whether the app can open a real database connection.

Interview language:

> I would mock repositories or services in unit tests, but I do not mock this database connection test because its purpose is to verify the real integration between Spring Boot, the JDBC driver, HikariCP, and PostgreSQL.

### Start PostgreSQL For The Integration Test

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts the real PostgreSQL dependency.
- Confirms it is accepting connections before running tests.

Result:

- PostgreSQL started.
- `pg_isready` reported it was accepting connections.

Interview language:

> Because this test uses a real database, I start PostgreSQL before running the backend test suite.

### Run The Backend Tests

```bash
cd backend
./mvnw test
```

Why:

- Verifies the JUnit assertion refactor.
- Confirms the database integration test still passes.

Result:

- Build succeeded.
- Tests run: 5.
- Failures: 0.
- Errors: 0.

Interview language:

> After changing the assertion style, I reran the backend test suite to verify behavior did not change.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops the database after the integration test run.

Result:

- PostgreSQL container and Compose network were removed.
- The named data volume was preserved.

Interview language:

> I stopped PostgreSQL after testing so no background service continued using local port 5432.

## Step 3.1: First Flyway Schema Migration

### Check The Starting State

```bash
git status --short
docker compose ps
find backend/src/main/resources backend/src/test/java -type f | sort
sed -n '1,120p' backend/src/main/resources/application.properties
```

Why:

- Confirms the working tree is clean before adding schema files.
- Checks whether PostgreSQL is already running.
- Lists the current backend resource and test files.
- Reviews the datasource and Flyway configuration before adding a migration.

Result:

- Git started clean.
- PostgreSQL was not running at first.
- The backend already had `application.properties`, a migration folder marker, and the earlier database test.
- Spring Boot was configured to use PostgreSQL, Flyway, and `spring.jpa.hibernate.ddl-auto=validate`.

Interview language:

> Before creating database tables, I checked the current project state and confirmed that Flyway was already enabled as the database migration tool.

### Add The Migration File

File added:

- `backend/src/main/resources/db/migration/V1__create_records_schema.sql`

File removed:

- `backend/src/main/resources/db/migration/.gitkeep`

Why:

- Flyway looks for migration files under `db/migration` by default.
- The filename `V1__create_records_schema.sql` means version `1`, followed by a description.
- The `.gitkeep` placeholder is no longer needed once the folder contains a real migration.

Schema created:

- `retention_policies`
- `records`

Important constraints:

- Primary keys use PostgreSQL `uuid` values.
- `retention_policies.name` is unique.
- `records.external_id` is unique.
- `records.retention_policy_id` is a foreign key to `retention_policies.id`.
- `records.status` is limited to `ACTIVE`, `ARCHIVED`, `PURGED`, or `FAILED`.
- Retention policy days must be positive.

Interview language:

> I used Flyway migrations so database structure is versioned in source control and applied consistently across local, test, and future cloud environments.

### Add A JUnit Schema Verification Test

File added:

- `backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java`

What the test verifies:

- Flyway created `flyway_schema_history`.
- Flyway created the `records` table.
- Flyway created the `retention_policies` table.
- PostgreSQL contains the `chk_records_status` check constraint.
- PostgreSQL contains the `fk_records_retention_policy` foreign key.

Why:

- This is another integration test because it validates the real database schema.
- It catches mistakes like a missing table, renamed constraint, or failed migration.

Interview language:

> I added an integration test around the schema because a migration is only useful if the application can apply it to the actual database successfully.

### Read Back The New Files

```bash
sed -n '1,220p' backend/src/main/resources/db/migration/V1__create_records_schema.sql
sed -n '1,220p' backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java
```

Why:

- Reads the migration and test files back in the terminal before running them.

Result:

- Confirmed the SQL migration and JUnit test were written as expected.

Interview language:

> I read new files back before testing so obvious content problems are caught early.

### Start PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts the real PostgreSQL dependency required by the integration tests.
- Checks that PostgreSQL is ready before Maven runs the Spring test context.
- Confirms the Compose service is healthy.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that the database was accepting connections.

Interview language:

> Because the schema test uses a real database, I started PostgreSQL first and verified readiness before running the backend test suite.

### Run The Backend Tests

```bash
cd backend
./mvnw test
```

Why:

- Runs the Spring Boot test suite.
- Lets Flyway apply the migration during application context startup.
- Proves the schema migration works with the real PostgreSQL database.

Result:

- Build succeeded.
- Tests run: 8.
- Failures: 0.
- Errors: 0.
- Flyway applied version `1 - create records schema`.

Interview language:

> The test run proved that Spring Boot can start, connect to PostgreSQL, apply the Flyway migration, and validate the resulting schema.

### Inspect The Database Directly

```bash
docker compose exec -T postgres psql -U governance -d governance -c '\dt'
docker compose exec -T postgres psql -U governance -d governance -c "select table_name from information_schema.tables where table_schema = 'public' order by table_name;"
docker compose exec -T postgres psql -U governance -d governance -c "select table_name, constraint_name, constraint_type from information_schema.table_constraints where table_schema = 'public' and table_name in ('records', 'retention_policies') order by table_name, constraint_name;"
docker compose exec -T postgres psql -U governance -d governance -c "select 'records' as table_name, count(*) as row_count from records union all select 'retention_policies', count(*) from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
```

Why:

- Uses `psql` inside the PostgreSQL container to inspect the real database.
- `\dt` lists database tables.
- `information_schema.tables` lists table metadata using SQL.
- `information_schema.table_constraints` lists constraints.
- The row count query confirms that the tables exist but do not contain business data yet.
- `flyway_schema_history` confirms which migrations have been applied.

Result:

- Tables present:
  - `flyway_schema_history`
  - `records`
  - `retention_policies`
- `records` and `retention_policies` both had `0` rows.
- Flyway showed migration version `1`, description `create records schema`, success `true`.

Interview language:

> I verified the migration in two ways: through automated JUnit tests and through direct PostgreSQL metadata queries.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops the local database after verification.
- Removes the container and Compose network.
- Preserves the named database volume.

Result:

- PostgreSQL stopped cleanly.

Interview language:

> I stopped the local service after verification so the project does not leave background containers running unnecessarily.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms no Compose services are still running.
- Shows the files changed by this phase.
- Gives a compact summary of the schema/test/documentation changes.

Result:

- Whitespace check passes.
- No Compose services were running.
- `git status --short` showed the removed `.gitkeep`, the updated command log, the new migration file, and the new schema test.
- `git diff --stat` summarized tracked changes only, which is why the untracked migration and test files appeared in `git status` but not yet in the diff stat.

Interview language:

> Before committing, I verify formatting, runtime cleanup, and the exact source-control diff.

### Stage And Commit The Phase

```bash
git add backend/src/main/resources/db/migration/.gitkeep backend/src/main/resources/db/migration/V1__create_records_schema.sql backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add initial records schema migration"
```

Why:

- Stages only the files related to the first schema migration.
- Reviews staged files before committing.
- Creates a focused checkpoint for the schema phase.

Result:

- Staged files were:
  - `backend/src/main/resources/db/migration/.gitkeep`
  - `backend/src/main/resources/db/migration/V1__create_records_schema.sql`
  - `backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.
- `git status --short` showed the placeholder deletion, migration addition, schema test addition, and command log update staged for commit.
- `git diff --cached --stat` summarized four staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed the schema migration as its own checkpoint so the database structure can be reviewed independently from the upcoming API code.

## Step 3.2: First JPA Entity And Repository

### Check The Starting State

```bash
git status --short
find backend/src/main/java backend/src/test/java backend/src/main/resources -type f | sort
sed -n '1,240p' backend/pom.xml
sed -n '1,180p' backend/src/main/resources/application.properties
sed -n '1,220p' backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java
```

Why:

- Confirms the repository is clean before starting.
- Lists the current backend source, test, and resource files.
- Confirms Spring Data JPA is already available in Maven.
- Reviews `spring.jpa.hibernate.ddl-auto=validate`.
- Reviews the schema test that proves the database tables already exist.

Result:

- Git started clean.
- The backend already had JPA and Flyway dependencies.
- The application was configured to validate schema instead of generating schema.
- The database schema migration already created `retention_policies`.

Interview language:

> Before adding persistence code, I verified that the schema exists through Flyway and that Hibernate is configured to validate mappings instead of changing the database automatically.

### Create The Retention Package Folders

```bash
mkdir -p backend/src/main/java/com/example/governance/retention backend/src/test/java/com/example/governance/retention
```

Why:

- Creates a focused package for retention-policy persistence code.
- Keeps domain code separate from generic database tests and API controllers.

Result:

- Added folders for main code and tests under `com.example.governance.retention`.

Interview language:

> I organized the persistence code by domain area so retention-policy logic has a clear home as the application grows.

### Add The JPA Entity

File added:

- `backend/src/main/java/com/example/governance/retention/RetentionPolicy.java`

What it does:

- Maps the Java class `RetentionPolicy` to the PostgreSQL table `retention_policies`.
- Uses `@Entity` to tell JPA this class is persisted.
- Uses `@Table(name = "retention_policies")` to match the real table name.
- Uses `@Id` and `@GeneratedValue(strategy = GenerationType.UUID)` for the primary key.
- Maps `retentionPeriodDays` to the database column `retention_period_days`.
- Uses validation annotations like `@NotBlank`, `@Size`, `@NotNull`, and `@Positive`.
- Keeps `createdAt` and `updatedAt` read-only from JPA inserts so the database defaults can set them.

Why:

- JPA entities are the Java representation of database rows.
- The entity must match the Flyway-created table because Hibernate is set to `validate`.

Interview language:

> I mapped the Flyway-managed table to a JPA entity so the application can work with retention policies as Java objects while the database remains the source of truth for schema.

### Add The Spring Data Repository

File added:

- `backend/src/main/java/com/example/governance/retention/RetentionPolicyRepository.java`

What it does:

```java
public interface RetentionPolicyRepository extends JpaRepository<RetentionPolicy, UUID> {

	Optional<RetentionPolicy> findByName(String name);
}
```

Why:

- `JpaRepository` gives standard CRUD operations like save, find by id, find all, and delete.
- `findByName` is a derived query method.
- Spring Data reads the method name and creates the SQL query implementation at runtime.

Interview language:

> I used Spring Data JPA to avoid writing boilerplate SQL for basic persistence while still keeping database rules enforced by PostgreSQL.

### Add Repository Integration Tests

File added:

- `backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java`

What the tests verify:

- A retention policy can be saved through the repository.
- A saved policy can be found by name.
- The database generates or receives a UUID primary key.
- PostgreSQL rejects duplicate policy names through the unique constraint.
- Test cleanup removes rows before and after each test.

Why:

- This test verifies the real connection between Spring Data JPA, Hibernate, Flyway, and PostgreSQL.
- A mock repository would not prove the entity mapping or database constraint works.

Interview language:

> I wrote a repository integration test because persistence code crosses an infrastructure boundary; I want proof that the Java mapping and real database schema work together.

### Read Back The New Files

```bash
sed -n '1,240p' backend/src/main/java/com/example/governance/retention/RetentionPolicy.java
sed -n '1,160p' backend/src/main/java/com/example/governance/retention/RetentionPolicyRepository.java
sed -n '1,240p' backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java
git status --short
```

Why:

- Reviews the files in the terminal before running tests.
- Confirms Git sees only the new retention package files.

Result:

- Verified the entity, repository, and repository test contents.
- Git showed new files under the retention package.

Interview language:

> I read the new files back and checked Git status before testing so I could confirm the change set stayed focused.

### Start PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts the real PostgreSQL database required by the repository integration test.
- Confirms PostgreSQL is accepting connections before Maven starts the Spring context.
- Confirms the container status.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that the database was accepting connections.

Interview language:

> Because this repository test talks to a real database, I start PostgreSQL and verify readiness before running Maven.

### Run The Backend Tests

```bash
cd backend
./mvnw test
```

Why:

- Compiles the new entity and repository.
- Starts the Spring test context.
- Lets Flyway validate the migration history.
- Lets Hibernate validate the entity mapping against the PostgreSQL schema.
- Runs the new repository integration tests.

Result:

- Build succeeded.
- Tests run: 10.
- Failures: 0.
- Errors: 0.
- Spring Data found `1` JPA repository interface.

Note:

- The duplicate-name test intentionally triggers a PostgreSQL unique constraint violation.
- Hibernate logs a duplicate-key warning during that test.
- The warning is expected because the test asserts that PostgreSQL rejects duplicate retention policy names.

Interview language:

> The test suite proves the persistence layer works end to end: Spring Data creates the repository, Hibernate validates the entity mapping, and PostgreSQL enforces the unique constraint.

### Improve Test Cleanup

File updated:

- `backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java`

Change:

```java
@BeforeEach
@AfterEach
void cleanTables() {
	jdbcTemplate.update("delete from records");
	jdbcTemplate.update("delete from retention_policies");
}
```

Why:

- The duplicate-name test intentionally inserts a row before checking the constraint.
- Cleaning before and after each test keeps the local database repeatable.

Command:

```bash
cd backend
./mvnw test
```

Result:

- Build still succeeded.
- Tests run: 10.
- Failures: 0.
- Errors: 0.

Interview language:

> I added cleanup around the integration test so test data does not leak into later tests or local development.

### Verify Database State After Tests

```bash
docker compose exec -T postgres psql -U governance -d governance -c "select count(*) as retention_policy_count from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
```

Why:

- Confirms the repository test cleanup left no business rows behind.
- Confirms Flyway still shows only the successful version 1 migration.
- Confirms PostgreSQL is still healthy before shutting it down.

Result:

- `retention_policies` contained `0` rows after the tests.
- Flyway still showed version `1`, description `create records schema`, success `true`.
- PostgreSQL was healthy.

Interview language:

> After integration tests write to a real database, I verify cleanup so the test environment remains deterministic.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops the database after the repository verification.
- Removes the container and Compose network.
- Preserves the named database volume.

Result:

- PostgreSQL stopped cleanly.

Interview language:

> I stop local infrastructure after verification so the development environment does not leave unused services running.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms no Compose services are running.
- Shows the files changed by the persistence phase.
- Gives a compact summary of the diff.

Result:

- Whitespace check passed.
- No Compose services were running.
- `git status --short` showed the command log update and new retention package files.
- `git diff --stat` showed the tracked command log change; the new Java files remained untracked until staging.

Interview language:

> Before committing, I verify formatting, runtime cleanup, and the exact source-control diff.

### Stage And Commit The Phase

```bash
git add backend/src/main/java/com/example/governance/retention/RetentionPolicy.java backend/src/main/java/com/example/governance/retention/RetentionPolicyRepository.java backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add retention policy persistence"
```

Why:

- Stages only the files related to the retention-policy persistence layer.
- Reviews staged files before committing.
- Creates a focused checkpoint before adding service or REST API code.

Result:

- Staged files were:
  - `backend/src/main/java/com/example/governance/retention/RetentionPolicy.java`
  - `backend/src/main/java/com/example/governance/retention/RetentionPolicyRepository.java`
  - `backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.
- `git diff --cached --stat` summarized four staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed the persistence layer separately from the REST API so the data-access foundation can be reviewed and understood independently.

## Step 3.3: Retention Policy Service Layer

### Simple Definitions

Repository:

- A Spring object that talks to the database for one entity.

Service:

- A Spring object that holds business rules before or after database work.

Mock:

- A fake object used in a unit test.
- In this phase, the repository is mocked so the service can be tested without PostgreSQL.

Unit test:

- Tests one class in isolation.

Integration test:

- Tests multiple real pieces together, such as Spring Boot plus PostgreSQL.

Interview language:

> I added a service layer so business rules do not live directly in controllers or repositories.

### Check The Starting State

```bash
git status --short
find backend/src/main/java/com/example/governance backend/src/test/java/com/example/governance -type f | sort
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicy.java
sed -n '1,120p' backend/src/main/java/com/example/governance/retention/RetentionPolicyRepository.java
sed -n '1,220p' backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java
```

Why:

- Confirms the repository is clean.
- Lists the current Java files.
- Reviews the entity, repository, and repository integration test before adding the service.

Result:

- Git started clean.
- The retention package already had the entity and repository.
- The repository already had `findByName`.

Interview language:

> I checked the existing persistence code before adding the service so the next layer uses the repository correctly.

### Add The Service And Exception

Files added:

- `backend/src/main/java/com/example/governance/retention/DuplicateRetentionPolicyException.java`
- `backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java`

What the service does:

- Checks whether a policy name already exists.
- Throws `DuplicateRetentionPolicyException` if the name exists.
- Saves a new `RetentionPolicy` if the name is available.
- Lists policies sorted by name.

Important code:

```java
repository.findByName(name)
		.ifPresent(existing -> {
			throw new DuplicateRetentionPolicyException(name);
		});
```

Meaning:

- Ask the repository if this name exists.
- If it exists, stop and throw an exception.
- If it does not exist, continue and save.

Interview language:

> The service is where I put business rules like duplicate-name checks, while the repository remains focused on database access.

### Add A Mocked Service Unit Test

File added:

- `backend/src/test/java/com/example/governance/retention/RetentionPolicyServiceTests.java`

What the test uses:

```java
@Mock
private RetentionPolicyRepository repository;

@InjectMocks
private RetentionPolicyService service;
```

Meaning:

- `@Mock` creates a fake repository.
- `@InjectMocks` creates the real service and gives it the fake repository.
- The test controls what the fake repository returns.

What the tests verify:

- A policy is saved when the name does not exist.
- A duplicate policy name throws `DuplicateRetentionPolicyException`.
- The service does not call `save` when the name is duplicated.
- Policies are listed by name.

Interview language:

> I used a mocked repository in the service unit test because I only wanted to test the service decision logic, not the database.

### Read Back The New Files

```bash
sed -n '1,120p' backend/src/main/java/com/example/governance/retention/DuplicateRetentionPolicyException.java
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java
sed -n '1,260p' backend/src/test/java/com/example/governance/retention/RetentionPolicyServiceTests.java
git status --short
```

Why:

- Reviews the new service, exception, and test files.
- Confirms Git sees only the three new files.

Result:

- Verified the new service-layer files.
- Git showed three untracked files.

Interview language:

> I read the files back and checked Git status before running tests so the change set stayed small.

### Run Only The Service Unit Test

```bash
cd backend
./mvnw -Dtest=RetentionPolicyServiceTests test
```

Why:

- Runs only the new service unit test.
- Does not require PostgreSQL because the repository is mocked.

Result:

- Build succeeded.
- Tests run: 3.
- Failures: 0.
- Errors: 0.

Interview language:

> I first ran the service test alone because mocked unit tests should be fast and independent from external services.

### Start PostgreSQL For The Full Test Suite

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts PostgreSQL for the existing database and repository integration tests.
- Confirms the database is accepting connections.
- Confirms the container is healthy.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that it was accepting connections.
- Docker Compose showed PostgreSQL as healthy.

Interview language:

> I only started PostgreSQL for the full suite because some tests are real database integration tests.

### Run The Full Backend Test Suite

```bash
cd backend
./mvnw test
```

Why:

- Runs every backend test.
- Confirms the new service unit test works with the existing database integration tests.

Result:

- Build succeeded.
- Tests run: 13.
- Failures: 0.
- Errors: 0.

Note:

- The duplicate-key warning is expected from the repository integration test.
- That test intentionally proves PostgreSQL rejects duplicate retention policy names.

Interview language:

> I ran both focused unit tests and the full test suite so the new service rule is verified by itself and the whole backend still works.

### Verify Database State After Tests

```bash
docker compose exec -T postgres psql -U governance -d governance -c "select count(*) as retention_policy_count from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
```

Why:

- Confirms the test suite left no business rows behind.
- Confirms Flyway still shows the successful version 1 migration.
- Confirms PostgreSQL is healthy before stopping it.

Result:

- `retention_policies` contained `0` rows after tests.
- Flyway showed version `1`, description `create records schema`, success `true`.
- PostgreSQL was healthy.

Interview language:

> After tests that touch a real database, I verify cleanup so future test runs start from a predictable state.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops PostgreSQL after verification.
- Removes the container and Compose network.
- Preserves the named database volume.

Result:

- PostgreSQL stopped cleanly.

Interview language:

> I stop local infrastructure after testing so no unused service keeps running.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms Docker Compose has no running services.
- Shows the files changed by this phase.
- Gives a compact summary of tracked changes.

Result:

- Whitespace check passed.
- No Compose services were running.
- `git status --short` showed the command log update and three new service-layer files.
- `git diff --stat` showed the tracked command log change; the new Java files remained untracked until staging.

Interview language:

> Before committing, I verify formatting, local infrastructure state, and the exact diff.

### Stage And Commit The Phase

```bash
git add backend/src/main/java/com/example/governance/retention/DuplicateRetentionPolicyException.java backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java backend/src/test/java/com/example/governance/retention/RetentionPolicyServiceTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add retention policy service"
```

Why:

- Stages only the service-layer files and command log.
- Reviews staged files before committing.
- Creates a focused checkpoint before adding REST API endpoints.

Result:

- Staged files were:
  - `backend/src/main/java/com/example/governance/retention/DuplicateRetentionPolicyException.java`
  - `backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java`
  - `backend/src/test/java/com/example/governance/retention/RetentionPolicyServiceTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.
- `git diff --cached --stat` summarized four staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed the service layer separately so the business rule can be reviewed independently from the API layer.

## Step 3.4: Retention Policy REST API

### Simple Definitions

Controller:

- A Spring object that receives HTTP requests.

Request DTO:

- A small object that represents JSON coming into the API.

Response DTO:

- A small object that controls JSON going out of the API.

MockMvc:

- A Spring test tool that lets tests call controllers like an HTTP client.

Easy memory sentence:

> Controller receives the request, service handles the rule, repository talks to the database.

### Check The Starting State

```bash
git status --short
find backend/src/main/java/com/example/governance backend/src/test/java/com/example/governance -type f | sort
sed -n '1,220p' backend/src/main/java/com/example/governance/api/ApplicationInfoController.java
sed -n '1,260p' backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicy.java
```

Why:

- Confirms Git is clean before starting.
- Reviews the existing controller style.
- Reviews the service that the new controller will call.
- Reviews the entity that will be converted into the API response.

Result:

- Git started clean.
- The existing API path style used `/api/v1/...`.
- The retention service already had create and list methods.

Interview language:

> Before exposing the feature through HTTP, I checked the existing API style and reused the service layer instead of calling the repository directly from the controller.

### Check The Spring Test Annotation

```bash
find ~/.m2/repository -path '*spring-boot-test*4.1.0*.jar' -print | sort | head -20
find ~/.m2/repository -path '*spring-test*7*.jar' -print | sort | head -20
find ~/.m2/repository -path '*spring-boot-webmvc-test*4.1.0*.jar' -print | sort | head -20
jar tf ~/.m2/repository/org/springframework/boot/spring-boot-test/4.1.0/spring-boot-test-4.1.0.jar | grep -E 'MockBean|MockitoBean'
jar tf ~/.m2/repository/org/springframework/spring-test/7.0.8/spring-test-7.0.8.jar | grep -E 'MockitoBean|MockBean'
jar tf ~/.m2/repository/org/springframework/boot/spring-boot-webmvc-test/4.1.0/spring-boot-webmvc-test-4.1.0.jar | grep -E 'WebMvcTest|AutoConfigureMockMvc|MockMvc' | head -40
```

Why:

- Confirms the correct Spring Boot 4 test package names.
- Verifies `@MockitoBean` is available from Spring Test.
- Verifies `@WebMvcTest` is available from Spring Boot WebMVC test support.

Result:

- `@MockitoBean` was found under `org.springframework.test.context.bean.override.mockito`.
- `@WebMvcTest` was found under `org.springframework.boot.webmvc.test.autoconfigure`.

Interview language:

> I checked the local dependency classes so the controller test used the correct Spring Boot 4 test annotations.

### Add The Request, Response, And Controller

Files added:

- `backend/src/main/java/com/example/governance/retention/CreateRetentionPolicyRequest.java`
- `backend/src/main/java/com/example/governance/retention/RetentionPolicyResponse.java`
- `backend/src/main/java/com/example/governance/retention/RetentionPolicyController.java`

Endpoints added:

```text
POST /api/v1/retention-policies
GET  /api/v1/retention-policies
```

What `POST` does:

- Receives JSON.
- Validates the JSON.
- Calls `service.createPolicy`.
- Returns HTTP `201 Created`.

What `GET` does:

- Calls `service.listPolicies`.
- Converts each policy into a response object.
- Returns a JSON array.

Interview language:

> I added REST endpoints so outside clients can create and list retention policies over HTTP.

### Add Controller Tests With A Mocked Service

File added:

- `backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java`

What the test uses:

```java
@WebMvcTest(RetentionPolicyController.class)
class RetentionPolicyControllerTests {

	@MockitoBean
	private RetentionPolicyService service;
}
```

Meaning:

- `@WebMvcTest` loads only the web/controller slice.
- `@MockitoBean` gives the controller a fake service.
- The test checks HTTP behavior without starting PostgreSQL.

What the tests verify:

- `POST /api/v1/retention-policies` returns `201 Created`.
- `GET /api/v1/retention-policies` returns a JSON list.
- Invalid create requests return `400 Bad Request`.

Interview language:

> I used a mocked service in the controller test because I wanted to test HTTP request and response behavior, not database behavior.

### Read Back The New Files

```bash
sed -n '1,180p' backend/src/main/java/com/example/governance/retention/CreateRetentionPolicyRequest.java
sed -n '1,240p' backend/src/main/java/com/example/governance/retention/RetentionPolicyController.java
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicyResponse.java
sed -n '1,280p' backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java
git status --short
```

Why:

- Reviews the new API files before running tests.
- Confirms Git sees only the new API files.

Result:

- Verified the request DTO, response DTO, controller, and controller test.
- Git showed four new untracked files.

Interview language:

> I read the new API files back before testing so the change set stayed easy to review.

### Run Only The Controller Test

```bash
cd backend
./mvnw -Dtest=RetentionPolicyControllerTests test
```

Why:

- Runs only the new controller test.
- Does not require PostgreSQL because the service is mocked.

Result:

- Build succeeded.
- Tests run: 3.
- Failures: 0.
- Errors: 0.

Note:

- The validation warning is expected from the bad-request test.
- It proves invalid JSON input returns HTTP `400`.

Interview language:

> I first ran the controller test alone because web-layer tests should be able to verify HTTP behavior without a real database.

### Start PostgreSQL For The Full Test Suite

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts PostgreSQL for the existing database and repository integration tests.
- Confirms the database is accepting connections before Maven runs all tests.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that PostgreSQL was accepting connections.

Interview language:

> The controller test uses a mocked service, but the full backend suite still needs PostgreSQL because other tests verify real database integration.

### Run The Full Backend Test Suite

```bash
cd backend
./mvnw test
```

Why:

- Runs all backend tests together.
- Confirms the new REST API works with the rest of the application.

Result:

- Build succeeded.
- Tests run: 16.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran the full test suite after the focused controller test so the new API layer did not break existing persistence and service behavior.

### Verify Database State After Tests

```bash
docker compose exec -T postgres psql -U governance -d governance -c "select count(*) as retention_policy_count from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
```

Why:

- Confirms test cleanup left no retention policy rows behind.
- Confirms Flyway still shows the successful version 1 migration.
- Confirms PostgreSQL is healthy before stopping it.

Result:

- `retention_policies` contained `0` rows after tests.
- Flyway showed version `1`, description `create records schema`, success `true`.
- PostgreSQL was healthy.

Interview language:

> After running tests that touch the real database, I verified cleanup and migration history.

### Stop PostgreSQL

```bash
docker compose down
```

Why:

- Stops PostgreSQL after verification.
- Removes the container and Compose network.
- Preserves the named database volume.

Result:

- PostgreSQL stopped cleanly.

Interview language:

> I stop local infrastructure after testing so no unused service keeps running.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms Docker Compose has no running services.
- Shows the files changed by this phase.
- Gives a compact summary of tracked changes.

Result:

- Whitespace check passed.
- No Compose services were running.
- `git status --short` showed the command log update and four new REST API files.
- `git diff --stat` showed the tracked command log change; the new Java files remained untracked until staging.

Interview language:

> Before committing, I verify formatting, local infrastructure state, and the exact diff.

### Stage And Commit The Phase

```bash
git add backend/src/main/java/com/example/governance/retention/CreateRetentionPolicyRequest.java backend/src/main/java/com/example/governance/retention/RetentionPolicyController.java backend/src/main/java/com/example/governance/retention/RetentionPolicyResponse.java backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add retention policy API"
```

Why:

- Stages only the REST API files and command log.
- Reviews staged files before committing.
- Creates a focused checkpoint before adding richer API error handling.

Result:

- Staged files were:
  - `backend/src/main/java/com/example/governance/retention/CreateRetentionPolicyRequest.java`
  - `backend/src/main/java/com/example/governance/retention/RetentionPolicyController.java`
  - `backend/src/main/java/com/example/governance/retention/RetentionPolicyResponse.java`
  - `backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.
- `git diff --cached --stat` summarized five staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed the REST API separately so the HTTP layer can be reviewed independently from persistence and business rules.

## Step 3.5: Separate Unit Tests From Integration Tests

### Simple Definitions

Surefire:

- Maven plugin that runs regular tests during `./mvnw test`.

Failsafe:

- Maven plugin that runs integration tests during `./mvnw verify`.

`*Tests`:

- Fast tests in this project.
- They should not need Docker.

`*IT`:

- Integration tests in this project.
- They can use real PostgreSQL.

Easy memory sentence:

> `test` is for fast tests; `verify` is for full checks including integration tests.

### Source References

Primary references used for this phase:

- [Maven Surefire Plugin: `surefire:test`](https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html)
- [Maven Failsafe Plugin: usage](https://maven.apache.org/surefire/maven-failsafe-plugin/usage.html)
- [Maven Failsafe Plugin: introduction](https://maven.apache.org/components/surefire-archives/surefire-LATEST/maven-failsafe-plugin/index.html)
- [Spring Boot reference: testing Spring Boot applications](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html)
- [Spring Framework reference: `@MockitoBean`](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-mockitobean.html)

What these sources confirm:

- Surefire runs regular tests in the Maven `test` phase.
- Surefire includes names like `*Test` and `*Tests` by default.
- Failsafe is designed for integration tests.
- Failsafe runs during `integration-test` and `verify`.
- Maven integration tests are normally run with `mvn verify`.
- Spring Boot recommends `@WebMvcTest` for focused Spring MVC controller tests.
- Spring `@MockitoBean` can replace a Spring bean with a Mockito mock in a test context.

### Check The Starting State

```bash
git status --short
find backend/src/test/java -type f | sort
sed -n '1,260p' backend/pom.xml
sed -n '1,220p' backend/src/test/java/com/example/governance/GovernancePlatformApplicationTests.java
sed -n '1,240p' backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
sed -n '1,220p' backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java
sed -n '1,260p' backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java
sed -n '1,260p' backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java
sed -n '1,220p' backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java
```

Why:

- Confirms Git is clean before changing test structure.
- Lists all current test files.
- Reviews Maven plugin config before adding Failsafe.
- Identifies which tests load the full Spring context or real PostgreSQL.

Result:

- Git started clean.
- `GovernancePlatformApplicationTests`, database tests, and repository tests used full Spring/PostgreSQL behavior.
- `RetentionPolicyControllerTests` already used `@WebMvcTest`.
- `ApplicationInfoControllerTests` still used full Spring context even though it only tested a controller.

Interview language:

> I reviewed the test suite first so only tests that need real infrastructure become integration tests.

### Rename Integration Tests To `*IT`

Files renamed:

- `GovernancePlatformApplicationTests.java` to `GovernancePlatformApplicationIT.java`
- `DatabaseConnectionTests.java` to `DatabaseConnectionIT.java`
- `DatabaseSchemaTests.java` to `DatabaseSchemaIT.java`
- `RetentionPolicyRepositoryTests.java` to `RetentionPolicyRepositoryIT.java`

Why:

- `*IT` is a common Maven naming convention for integration tests.
- These tests either load the full Spring context or touch real PostgreSQL.
- Renaming them keeps them out of the fast `./mvnw test` command.

Result:

- Full-context/database tests now end with `IT`.

Interview language:

> I renamed database-backed tests to `*IT` so Maven can separate fast tests from infrastructure-dependent tests.

### Configure Maven Failsafe

File updated:

- `backend/pom.xml`

Plugin added:

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-failsafe-plugin</artifactId>
	<executions>
		<execution>
			<goals>
				<goal>integration-test</goal>
				<goal>verify</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

Why:

- Surefire runs regular tests during the `test` phase.
- Failsafe runs integration tests during the `integration-test` and `verify` phases.
- This makes `./mvnw verify` run the renamed `*IT` files.

Result:

- Maven now has a separate integration-test runner.

Interview language:

> I configured Maven Failsafe so integration tests run in the proper Maven lifecycle phase instead of being mixed into the fast test phase.

### Convert Info Controller Test To A Web Slice

File updated:

- `backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java`

Change:

```java
@WebMvcTest(ApplicationInfoController.class)
class ApplicationInfoControllerTests {
```

Why:

- This test only checks HTTP behavior for one controller.
- It does not need the full Spring application context.
- It does not need PostgreSQL.

Result:

- `ApplicationInfoControllerTests` stays as a fast `*Tests` file.

Interview language:

> I converted the info controller test to a web-slice test because controller behavior can be tested without starting the full application or database.

### Read Back The Edited Files

```bash
find backend/src/test/java -type f | sort
sed -n '80,150p' backend/pom.xml
sed -n '1,120p' backend/src/test/java/com/example/governance/GovernancePlatformApplicationIT.java
sed -n '1,140p' backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java
git status --short
git diff -- backend/pom.xml backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java | sed -n '1,220p'
```

Why:

- Confirms the new test file names.
- Confirms the Failsafe plugin is present.
- Confirms the info controller test uses `@WebMvcTest`.
- Reviews the focused diff.

Result:

- Test files were split into `*Tests` and `*IT`.
- Git showed the expected renames plus Maven and controller-test changes.

Interview language:

> I read the edited files back and reviewed the diff before running the separated Maven commands.

### Run Fast Tests Without Docker

```bash
docker compose ps
cd backend
./mvnw test
```

Why:

- Confirms no Docker services are running.
- Proves `./mvnw test` no longer needs PostgreSQL.
- Runs only the fast `*Tests` classes.

Result:

- Docker Compose showed no running services.
- Build succeeded.
- Surefire ran 9 tests.
- Failures: 0.
- Errors: 0.
- Failsafe was downloaded the first time because it was newly added to `pom.xml`.

Interview language:

> I verified that the regular test command is now fast and does not require external infrastructure.

### Run Full Verification With PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
cd backend
./mvnw verify
```

Why:

- Starts PostgreSQL for integration tests.
- Confirms the database is accepting connections.
- Runs Surefire tests first.
- Packages the application.
- Runs Failsafe integration tests second.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that PostgreSQL was accepting connections.
- Surefire ran 9 fast tests.
- Failsafe ran 7 integration tests.
- Build succeeded.
- Failures: 0.
- Errors: 0.

Interview language:

> I verified the full Maven lifecycle: unit and web tests run under Surefire, while database integration tests run under Failsafe during `verify`.

### Verify Database Cleanup

```bash
cd ..
docker compose exec -T postgres psql -U governance -d governance -c "select count(*) as retention_policy_count from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
docker compose down
```

Why:

- Confirms integration tests left no business rows behind.
- Confirms Flyway migration history is still valid.
- Confirms PostgreSQL is healthy before shutdown.
- Stops local infrastructure after verification.

Result:

- `retention_policies` contained `0` rows.
- Flyway showed version `1`, description `create records schema`, success `true`.
- PostgreSQL stopped cleanly.

Interview language:

> After integration tests touched the real database, I verified cleanup and stopped local infrastructure.

### Update Developer Documentation

File updated:

- `README.md`

Commands documented:

```bash
cd backend
./mvnw test
docker compose up -d postgres
./mvnw verify
cd ..
docker compose down
```

Why:

- The everyday testing workflow changed.
- Developers should know which command needs Docker and which command does not.

Result:

- README now says `*Tests` run with `./mvnw test`.
- README now says `*IT` files run with `./mvnw verify` and require PostgreSQL.

Interview language:

> I updated the README when the test workflow changed so the project remains easy for another developer to run.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms Docker Compose has no running services.
- Shows the files changed by this phase.
- Gives a compact summary of tracked changes.

Result:

- Whitespace check passed.
- No Compose services were running.
- `git status --short` showed the README update, Maven config change, command log update, test renames, and info controller test update.
- `git diff --stat` showed tracked changes; the new `*IT` files remained untracked until staging.

Interview language:

> Before committing, I verify formatting, local infrastructure state, and the exact diff.

### Stage And Commit The Phase

```bash
git add README.md backend/pom.xml backend/src/test/java/com/example/governance/GovernancePlatformApplicationTests.java backend/src/test/java/com/example/governance/GovernancePlatformApplicationIT.java backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java backend/src/test/java/com/example/governance/database/DatabaseConnectionTests.java backend/src/test/java/com/example/governance/database/DatabaseConnectionIT.java backend/src/test/java/com/example/governance/database/DatabaseSchemaTests.java backend/src/test/java/com/example/governance/database/DatabaseSchemaIT.java backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryTests.java backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryIT.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "test: separate unit and integration tests"
```

Why:

- Stages only the test-separation files and documentation.
- Reviews staged files before committing.
- Creates a focused checkpoint for the new test workflow.

Result:

- Staged files were:
  - `README.md`
  - `backend/pom.xml`
  - `backend/src/test/java/com/example/governance/GovernancePlatformApplicationIT.java`
  - `backend/src/test/java/com/example/governance/api/ApplicationInfoControllerTests.java`
  - `backend/src/test/java/com/example/governance/database/DatabaseConnectionIT.java`
  - `backend/src/test/java/com/example/governance/database/DatabaseSchemaIT.java`
  - `backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryIT.java`
  - `docs/command-log.md`
- Git detected the old `*Tests` files as renames to the new `*IT` files.
- Staged whitespace check passed.
- `git diff --cached --stat` summarized eight staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed the test separation as its own checkpoint because it changes how developers run and reason about the test suite.

## Step 3.6: Structured API Error Responses

### Simple Definitions

API error response:

- The JSON body returned when something goes wrong in the API.

Global exception handler:

- A shared Spring class that catches exceptions from controllers and turns them into HTTP responses.

Validation error:

- A request problem, such as a blank name or a number that must be greater than zero.

Conflict error:

- A request that is valid JSON but cannot be completed because it breaks a business rule, such as creating a duplicate policy name.

Easy memory sentence:

> The controller receives the request, and the exception handler formats errors when something goes wrong.

### Source References

Primary references used for this phase:

- [Spring Framework reference: controller advice](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-advice.html)
- [Spring Framework reference: exception handler methods](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)
- [Spring Framework API: `ControllerAdvice`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html)
- [Spring Framework API: `MethodArgumentNotValidException`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/MethodArgumentNotValidException.html)
- [Spring Framework API: `ResponseEntityExceptionHandler`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html)

What these sources confirm:

- `@ControllerAdvice` and `@RestControllerAdvice` can apply exception handling across controllers.
- `@ExceptionHandler` methods can catch selected exception types.
- Exception handler return values can include `ResponseEntity`.
- `MethodArgumentNotValidException` is raised when `@Valid` request validation fails.

Interview language:

> I used Spring controller advice so API error formatting is centralized instead of duplicated inside every controller.

### Add Shared API Error Classes

Files added:

- `backend/src/main/java/com/example/governance/api/ApiErrorResponse.java`
- `backend/src/main/java/com/example/governance/api/FieldValidationError.java`

What they do:

- `ApiErrorResponse` is the common JSON shape for API errors.
- `FieldValidationError` represents one invalid request field.

Error response shape:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Request validation failed",
  "path": "/api/v1/retention-policies",
  "fieldErrors": []
}
```

Why:

- Clients receive predictable error JSON.
- Future frontend code can show useful error messages.
- Future controllers can reuse the same response format.

Interview language:

> I introduced a shared API error response so clients receive consistent JSON errors across endpoints.

### Add The Global Exception Handler

File added:

- `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`

What it handles:

- `DuplicateRetentionPolicyException` becomes HTTP `409 Conflict`.
- `MethodArgumentNotValidException` becomes HTTP `400 Bad Request`.

Important annotations:

```java
@RestControllerAdvice
@ExceptionHandler(DuplicateRetentionPolicyException.class)
@ExceptionHandler(MethodArgumentNotValidException.class)
```

Why:

- The controller stays focused on successful requests.
- Error behavior is handled in one shared place.
- `409 Conflict` is clearer than returning a generic server error for duplicate names.

Interview language:

> I added a global exception handler to translate application exceptions into meaningful HTTP responses.

### Update Controller Tests

File updated:

- `backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java`

Changes:

- Imported `ApiExceptionHandler` into the web-slice test.
- Expanded the bad-request test to assert the structured `400` response body.
- Added a duplicate-name test that asserts a structured `409` response body.

Commands used to read back the files:

```bash
sed -n '1,180p' backend/src/main/java/com/example/governance/api/ApiErrorResponse.java
sed -n '1,240p' backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java
sed -n '1,120p' backend/src/main/java/com/example/governance/api/FieldValidationError.java
sed -n '1,260p' backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java
git status --short
```

Why:

- Confirms the new error classes and updated test are correct before running tests.
- Confirms Git sees only the expected files.

Result:

- Verified the new error response classes.
- Verified the controller test now checks `400` and `409` structured error JSON.

Interview language:

> I updated controller tests so API error behavior is now part of the contract, not just an implementation detail.

### Run Focused Controller Test

```bash
cd backend
./mvnw -Dtest=RetentionPolicyControllerTests test
```

Why:

- Runs only the controller test.
- Verifies the new structured error responses without needing PostgreSQL.

Result:

- Build succeeded.
- Tests run: 4.
- Failures: 0.
- Errors: 0.

Interview language:

> I first ran the focused controller test to verify the HTTP error contract quickly without starting infrastructure.

### Run Fast Tests

```bash
cd backend
./mvnw test
```

Why:

- Runs all fast `*Tests` classes.
- Confirms the shared exception handler did not break existing controller or service tests.

Result:

- Build succeeded.
- Tests run: 10.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran the fast test suite to confirm the new shared error handling still works with all non-database tests.

### Run Full Verification With PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
cd backend
./mvnw verify
```

Why:

- Starts PostgreSQL for integration tests.
- Confirms database readiness.
- Runs fast tests through Surefire.
- Runs integration tests through Failsafe.

Result:

- PostgreSQL started successfully.
- `pg_isready` reported that PostgreSQL was accepting connections.
- Surefire ran 10 fast tests.
- Failsafe ran 7 integration tests.
- Build succeeded.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran full verification after changing shared API behavior so both web tests and database integration tests still pass.

### Verify Database Cleanup

```bash
cd ..
docker compose exec -T postgres psql -U governance -d governance -c "select count(*) as retention_policy_count from retention_policies;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
docker compose down
```

Why:

- Confirms integration tests left no business rows behind.
- Confirms Flyway migration history is still valid.
- Stops local infrastructure after verification.

Result:

- `retention_policies` contained `0` rows.
- Flyway showed version `1`, description `create records schema`, success `true`.
- PostgreSQL stopped cleanly.

Interview language:

> After full verification touched the real database, I confirmed cleanup and shut down local infrastructure.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms Docker Compose has no running services.
- Shows the files changed by this phase.
- Gives a compact summary of tracked changes.

Result:

- Whitespace check passed.
- No Compose services were running.
- `git status --short` showed the updated controller test, command log update, and three new API error classes.
- `git diff --stat` showed tracked changes; the new API error classes remained untracked until staging.

Interview language:

> Before committing, I verify formatting, local infrastructure state, and the exact diff.

### Stage And Commit The Phase

```bash
git add backend/src/main/java/com/example/governance/api/ApiErrorResponse.java backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java backend/src/main/java/com/example/governance/api/FieldValidationError.java backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add structured API error responses"
```

Why:

- Stages only the API error handling files and command log.
- Reviews staged files before committing.
- Creates a focused checkpoint before moving to records CRUD.

Result:

- Staged files were:
  - `backend/src/main/java/com/example/governance/api/ApiErrorResponse.java`
  - `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`
  - `backend/src/main/java/com/example/governance/api/FieldValidationError.java`
  - `backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java`
  - `docs/command-log.md`
- Staged whitespace check passed.
- `git diff --cached --stat` summarized five staged files.
- The extra `git add docs/command-log.md` restages this log after recording the staged-file inspection.

Interview language:

> I committed API error handling separately so the error contract can be reviewed independently from future business features.

## Step 4.1: Records API Feature Slice

This step builds phases 1 to 8 of the records feature in one larger checkpoint, but the code is still organized as small layers so it can be studied one piece at a time.

### Phase Map

1. Add record status values.
2. Add the record database entity.
3. Add the record repository.
4. Add repository integration tests against PostgreSQL.
5. Add the record service.
6. Add service unit tests with Mockito.
7. Add request and response DTOs.
8. Add the REST controller, error handling, and controller tests.

Interview language:

> I built the records feature vertically, from database mapping to repository, service, API contract, and tests.

### Official References Used

- Spring Data JPA repository core concepts: https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html
- Spring Data JPA query methods: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
- Spring MVC request mapping: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html
- Spring Bean Validation integration: https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html
- Spring MockMvc testing: https://docs.spring.io/spring-framework/reference/testing/mockmvc.html
- Jakarta Persistence `@Entity`: https://jakarta.ee/specifications/platform/11/apidocs/jakarta/persistence/entity
- Jakarta Persistence `@ManyToOne`: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/manytoone
- Jakarta Persistence enum mapping: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/enumtype

### Inspect The Current Backend

```bash
git status --short
find backend/src/main/java/com/example/governance backend/src/test/java/com/example/governance backend/src/main/resources -type f
sed -n '1,220p' backend/src/main/resources/db/migration/V1__create_records_schema.sql
sed -n '1,240p' backend/src/main/java/com/example/governance/retention/RetentionPolicy.java
sed -n '1,220p' backend/src/main/java/com/example/governance/retention/RetentionPolicyService.java
sed -n '1,260p' backend/src/main/java/com/example/governance/retention/RetentionPolicyController.java
sed -n '1,220p' backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java
sed -n '1,280p' backend/src/test/java/com/example/governance/retention/RetentionPolicyControllerTests.java
sed -n '1,240p' backend/src/test/java/com/example/governance/retention/RetentionPolicyServiceTests.java
sed -n '1,260p' backend/src/test/java/com/example/governance/retention/RetentionPolicyRepositoryIT.java
```

Why:

- Confirms the working tree before editing.
- Uses `find` instead of `rg` because this machine does not currently have `rg` available.
- Reads the retention feature first so the records feature follows the same style.

Result:

- Confirmed the existing Flyway migration already has a `records` table.
- Confirmed retention already has the same controller, service, repository, and test pattern we can reuse.

Interview language:

> I inspected the existing code before adding records so the new feature matched the application's current structure.

### Create The Records Package

```bash
mkdir -p backend/src/main/java/com/example/governance/records backend/src/test/java/com/example/governance/records
```

Why:

- Creates a focused package for the records domain.
- Keeps production code and test code in matching package paths.

Result:

- Created the records source and test directories.

Interview language:

> I grouped the records code by feature so related domain code stays easy to find.

### Add Record Domain And Persistence Files

Files added:

- `backend/src/main/java/com/example/governance/records/RecordStatus.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecord.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java`

What each file does:

- `RecordStatus` lists allowed lifecycle states: `ACTIVE`, `ARCHIVED`, `PURGED`, and `FAILED`.
- `GovernanceRecord` maps the `records` table to a Java object.
- `GovernanceRecordRepository` gives Spring Data JPA the database access interface.
- `GovernanceRecordRepositoryIT` proves the repository works with real PostgreSQL.

Important annotations and APIs:

```java
@Entity
@Table(name = "records")
@Enumerated(EnumType.STRING)
@ManyToOne(fetch = FetchType.LAZY)
public interface GovernanceRecordRepository extends JpaRepository<GovernanceRecord, UUID>
Optional<GovernanceRecord> findByExternalId(String externalId)
```

Why:

- `@Entity` tells JPA this Java class is stored in a database table.
- `@Enumerated(EnumType.STRING)` stores enum names like `ACTIVE`, which is more readable than storing numbers.
- `@ManyToOne` models that many records can share one retention policy.
- `JpaRepository` gives common database methods such as `save`, `findAll`, and `findById`.
- `findByExternalId` is a Spring Data query method; Spring builds the query from the method name.

Interview language:

> I used a JPA entity for the database mapping and a Spring Data repository so the application can persist records without hand-writing common CRUD SQL.

### Add Record Service And Unit Tests

Files added:

- `backend/src/main/java/com/example/governance/records/DuplicateRecordException.java`
- `backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordService.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java`

What the service does:

- Creates records as `ACTIVE`.
- Rejects duplicate `externalId` values.
- Allows records without a retention policy.
- Rejects a retention policy ID that does not exist.
- Lists records sorted by `externalId`.

Why:

- The service is where business rules live.
- The controller should not decide business rules.
- The repository should not decide business rules.
- Unit tests use fake repositories so the service rules can be tested without PostgreSQL.

Interview language:

> I put business decisions in the service layer and used unit tests to verify those decisions without needing infrastructure.

### Add Record API Contract

Files added:

- `backend/src/main/java/com/example/governance/records/CreateRecordRequest.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordController.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java`

File updated:

- `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`

Endpoints added:

```text
POST /api/v1/records
GET /api/v1/records
```

Important annotations and APIs:

```java
@RestController
@RequestMapping("/api/v1/records")
@PostMapping
@GetMapping
@Valid
@RequestBody
@WebMvcTest(GovernanceRecordController.class)
MockMvc
jsonPath(...)
```

Why:

- Request DTOs protect the API from exposing database entities directly.
- Response DTOs control exactly what JSON clients receive.
- `@Valid` activates validation annotations like `@NotBlank`.
- `MockMvc` tests controller behavior without starting a real server.
- The global exception handler converts service exceptions into clear HTTP errors.

Interview language:

> I separated API DTOs from database entities and tested the controller as an HTTP contract.

### Add Study Comments To Source Code

Files commented:

- `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`
- `backend/src/main/java/com/example/governance/records/CreateRecordRequest.java`
- `backend/src/main/java/com/example/governance/records/DuplicateRecordException.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecord.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordController.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordService.java`
- `backend/src/main/java/com/example/governance/records/RecordStatus.java`
- `backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java`

Why:

- Comments explain the purpose of each layer in plain language.
- Comments are placed near the code they explain.
- Comments focus on interview concepts: API contract, business rules, repositories, database constraints, and test type.

Interview language:

> I added comments that explain the intent of the code, especially around the boundaries between controller, service, repository, and database tests.

### Read Back The New Records Files

```bash
find backend/src/main/java/com/example/governance/records backend/src/test/java/com/example/governance/records -type f
sed -n '1,280p' backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecord.java
sed -n '1,240p' backend/src/main/java/com/example/governance/records/GovernanceRecordService.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordController.java
sed -n '1,220p' backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java
sed -n '1,180p' backend/src/main/java/com/example/governance/records/CreateRecordRequest.java
sed -n '1,180p' backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java
sed -n '1,160p' backend/src/main/java/com/example/governance/records/RecordStatus.java
sed -n '1,120p' backend/src/main/java/com/example/governance/records/DuplicateRecordException.java
sed -n '1,120p' backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java
sed -n '1,320p' backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java
sed -n '1,320p' backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java
sed -n '1,260p' backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java
```

Why:

- Reads source files through the terminal after editing.
- Confirms the comments and code are present before test execution.

Result:

- Verified the new records files and the updated global exception handler.

Interview language:

> I read the files back after editing so I could verify the implementation before running tests.

### Run Focused Records Tests

```bash
cd backend
./mvnw -Dtest=GovernanceRecordServiceTests,GovernanceRecordControllerTests test
```

Why:

- Runs only the fast records service and controller tests.
- Avoids PostgreSQL while checking business logic and API behavior.

Result:

- Build succeeded.
- Tests run: 10.
- Failures: 0.
- Errors: 0.

Interview language:

> I first ran focused tests for the new feature so feedback was fast and easy to understand.

### Run Fast Backend Tests

```bash
cd backend
./mvnw test
```

Why:

- Runs every `*Tests` class.
- Confirms the new records feature did not break existing fast tests.

Result:

- Build succeeded.
- Tests run: 20.
- Failures: 0.
- Errors: 0.

After the comment-only pass, the same command was run again:

```bash
cd backend
./mvnw test
```

Result:

- Build succeeded.
- Tests run: 20.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran the fast test suite after adding the feature and again after the comment pass to keep the checkpoint clean.

### Run Full Verification With PostgreSQL

```bash
cd backend
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
./mvnw verify
```

Why:

- Starts the real PostgreSQL database.
- Confirms PostgreSQL is accepting connections.
- Runs fast tests with Surefire.
- Runs integration tests with Failsafe.

Result:

- PostgreSQL started successfully and was healthy.
- Surefire ran 20 fast tests.
- Failsafe ran 10 integration tests.
- Build succeeded.
- Failures: 0.
- Errors: 0.

Note:

- The duplicate-key warnings in the repository integration tests are expected because those tests intentionally prove the database rejects duplicates.

Interview language:

> I ran full verification with PostgreSQL because repository integration tests should prove the JPA mapping and database constraints work together.

### Verify Database Cleanup

```bash
cd backend
docker compose exec -T postgres psql -U governance -d governance -c "select 'records' as table_name, count(*) as row_count from records union all select 'retention_policies', count(*) from retention_policies order by table_name;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
docker compose down
```

Why:

- Checks that integration tests did not leave business rows behind.
- Confirms Flyway migration history is valid.
- Stops PostgreSQL after verification.

Result:

- `records` row count: `0`.
- `retention_policies` row count: `0`.
- Flyway version `1`, description `create records schema`, success `true`.
- PostgreSQL stopped cleanly.

Interview language:

> I verified database cleanup after integration tests so the test suite remains repeatable.

### Final Checks Before Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
```

Why:

- Checks for whitespace problems.
- Confirms no Docker Compose services are still running.
- Shows which files changed before staging.
- Gives a compact tracked-file diff summary.

Result:

- Whitespace check passed.
- Docker Compose showed no running services.
- Git showed the updated API exception handler, command log, and new records packages.

Interview language:

> I checked formatting, local infrastructure state, and Git status before creating the checkpoint.

### Stage And Commit The Records Phase

```bash
git add backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java backend/src/main/java/com/example/governance/records/CreateRecordRequest.java backend/src/main/java/com/example/governance/records/DuplicateRecordException.java backend/src/main/java/com/example/governance/records/GovernanceRecord.java backend/src/main/java/com/example/governance/records/GovernanceRecordController.java backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java backend/src/main/java/com/example/governance/records/GovernanceRecordService.java backend/src/main/java/com/example/governance/records/RecordStatus.java backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git status --short
git diff --cached --stat
git add docs/command-log.md
git commit -m "feat: add records API"
```

Why:

- Stages only the records feature files and the command log.
- Reviews the staged files before committing.
- Restages the command log after recording the staged-file commands.
- Creates one checkpoint for the full records API slice.

Result:

- Staged files: 14.
- Staged whitespace check passed.
- Staged diff summary: 14 files changed.

Interview language:

> I committed the records feature as one vertical slice because the entity, repository, service, API, and tests belong to the same user-facing capability.

## Step 4.1a: Add Study Comments To Records Files

This step adds more comments inside the records feature files. The goal is not to change behavior. The goal is to make the code easier to study file by file.

### Inspect The Current State

```bash
git status --short
find backend/src/main/java/com/example/governance/records backend/src/test/java/com/example/governance/records -type f
sed -n '1,260p' backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java
tail -n 260 docs/command-log.md
```

Why:

- Confirms the working tree is clean before the comment-only edit.
- Lists the records files that need comments.
- Reads the shared exception handler because it is part of records API error behavior.
- Checks where to append the new command-log section.

Result:

- Git started clean.
- Found the records production files, records test files, and shared exception handler to comment.

Interview language:

> I checked the working tree and read the files first so this documentation pass stayed scoped to the records feature.

### Read The Files Before Editing

```bash
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecord.java
sed -n '1,240p' backend/src/main/java/com/example/governance/records/GovernanceRecordService.java
sed -n '1,220p' backend/src/main/java/com/example/governance/records/GovernanceRecordController.java
sed -n '1,220p' backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java
sed -n '1,180p' backend/src/main/java/com/example/governance/records/CreateRecordRequest.java
sed -n '1,180p' backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java
sed -n '1,120p' backend/src/main/java/com/example/governance/records/RecordStatus.java
sed -n '1,120p' backend/src/main/java/com/example/governance/records/DuplicateRecordException.java
sed -n '1,120p' backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java
sed -n '1,340p' backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java
sed -n '1,360p' backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java
sed -n '1,300p' backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java
```

Why:

- Reads each file before editing so comments explain the real code.
- Helps place comments near the blocks they explain.

Result:

- Identified the main comment targets: JPA mapping, service rules, DTO boundary, controller routes, exception handling, and test structure.

Interview language:

> I reviewed the source before editing so the comments explain intent, not just repeat syntax.

### Add Comments Inside Source Files

Files updated:

- `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`
- `backend/src/main/java/com/example/governance/records/CreateRecordRequest.java`
- `backend/src/main/java/com/example/governance/records/DuplicateRecordException.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecord.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordController.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordService.java`
- `backend/src/main/java/com/example/governance/records/RecordStatus.java`
- `backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java`

What the comments explain:

- Which layer each file belongs to.
- Why controllers should stay thin.
- Why business rules live in services.
- Why repositories are database access interfaces.
- Why DTOs protect the API boundary.
- Why JPA entity mappings must match Flyway SQL.
- Why tests use mocks or real PostgreSQL depending on the test type.
- Which details are important for interviews, marked with `IMPORTANT`.

Interview language:

> I added comments that explain architecture boundaries: entity, repository, service, DTO, controller, exception handler, unit test, and integration test.

### Read Back And Test The Comment Pass

```bash
git diff --check
git status --short
git diff --stat
sed -n '1,300p' backend/src/main/java/com/example/governance/records/GovernanceRecord.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordService.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordController.java
sed -n '1,360p' backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java
sed -n '1,380p' backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java
cd backend
./mvnw test
```

Why:

- `git diff --check` confirms no whitespace mistakes.
- `git status --short` confirms only intended files changed.
- `git diff --stat` confirms this is a comment-sized change.
- `sed` reads the most important files back after editing.
- `./mvnw test` compiles production and test code, then runs the fast test suite.

Result:

- Whitespace check passed.
- 13 files changed with comment additions only.
- Maven build succeeded.
- Tests run: 20.
- Failures: 0.
- Errors: 0.

Interview language:

> Even though I only changed comments, I still compiled and ran the fast tests to prove the project stayed healthy.

### Final Checks And Commit

```bash
git diff --check
git status --short
git diff --stat
docker compose ps
git add backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java backend/src/main/java/com/example/governance/records/CreateRecordRequest.java backend/src/main/java/com/example/governance/records/DuplicateRecordException.java backend/src/main/java/com/example/governance/records/GovernanceRecord.java backend/src/main/java/com/example/governance/records/GovernanceRecordController.java backend/src/main/java/com/example/governance/records/GovernanceRecordRepository.java backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java backend/src/main/java/com/example/governance/records/GovernanceRecordService.java backend/src/main/java/com/example/governance/records/RecordStatus.java backend/src/main/java/com/example/governance/records/RetentionPolicyNotFoundException.java backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java backend/src/test/java/com/example/governance/records/GovernanceRecordRepositoryIT.java backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git diff --cached --stat
git status --short
git add docs/command-log.md
git commit -m "docs: add records code study comments"
```

Why:

- Confirms the final diff is clean before staging.
- Confirms Docker Compose is not running.
- Stages only the comment and command-log files.
- Reviews staged files before committing.
- Creates a dedicated checkpoint for the study comments.

Result:

- Whitespace check passed.
- Docker Compose showed no running services.
- Diff summary showed 14 changed files.
- Staged files: 14.
- Staged whitespace check passed.

Interview language:

> I kept the comment pass as a separate commit so the learning notes are easy to review apart from behavior changes.

## Step 4.2: Manual Records API HTTP Verification

This step runs the real backend with the real PostgreSQL database, then calls the records endpoints with `curl`. No application code changed in this step. The goal is to prove the API works outside unit tests.

### Official References Used

- curl command-line options: https://everything.curl.dev/cmdline/options/index.html
- Docker Compose `up`: https://docs.docker.com/reference/cli/docker/compose/up/
- Docker Compose `exec`: https://docs.docker.com/reference/cli/docker/compose/exec/
- Docker Compose `down`: https://docs.docker.com/reference/cli/docker/compose/down/
- Spring Boot Actuator endpoints and health: https://docs.enterprise.spring.io/spring-boot/reference/actuator/endpoints.html

### Inspect The Current State

```bash
git status --short
sed -n '1,220p' backend/src/main/resources/application.properties
sed -n '1,260p' backend/docker-compose.yml
find backend/src/main/java/com/example/governance -type f
find . -maxdepth 3 -name 'docker-compose.yml' -o -name 'compose.yml' -o -name 'docker-compose.yaml'
find . -type f | grep -E '(^|/)(docker-compose|compose)\.(ya?ml)$'
ls -la
ls -la backend
find . -maxdepth 4 -type f | sort | sed -n '1,160p'
sed -n '1,220p' compose.yaml
sed -n '1,160p' .env.example
```

Why:

- Confirms Git is clean before the phase.
- Reads the backend database settings and application name.
- Locates the Compose file.
- Reads the Compose service name, PostgreSQL port, database name, username, and password defaults.

Result:

- Git started clean.
- `application.properties` points to `jdbc:postgresql://localhost:5432/governance`.
- The Compose file is `compose.yaml` at the repository root.
- `backend/docker-compose.yml` did not exist, so the correct file path is the root `compose.yaml`.

Interview language:

> I inspected configuration before running the app so the backend, database, and HTTP calls all used the same connection details.

### Start PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
```

Why:

- Starts PostgreSQL in the background.
- Confirms PostgreSQL is accepting connections.
- Checks the container status before starting Spring Boot.

Result:

- PostgreSQL started successfully.
- `pg_isready` returned `accepting connections`.
- The PostgreSQL container became healthy.

Interview language:

> I started the database first because the backend validates its schema and opens a database connection during startup.

### Start The Backend

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Why:

- Runs the Spring Boot application from source.
- Uses the Maven wrapper so local Maven installation is not required.
- Overrides the port to `8081` to avoid conflicts with anything already using `8080`.

Result:

- Spring Boot started successfully.
- Tomcat listened on port `8081`.
- Flyway validated migration version `1`.
- Hibernate connected to PostgreSQL.

Interview language:

> I ran the backend locally on a temporary port and verified that it connected to PostgreSQL before testing the API.

### Clean Manual-Test Rows

```bash
docker compose exec -T postgres psql -U governance -d governance -c "delete from records; delete from retention_policies;"
```

Why:

- Starts the manual test from a known database state.
- Deletes child rows first because records can reference retention policies.

Result:

- `records` deleted rows: `0`.
- `retention_policies` deleted rows: `0`.

Interview language:

> I cleaned test data first so the manual API verification was repeatable.

### Diagnose Local Curl Access

```bash
curl -sS -i http://localhost:8081/api/v1/info
curl -sS -i http://127.0.0.1:8081/api/v1/info
lsof -nP -iTCP:8081 -sTCP:LISTEN
ps -p 58525 -o pid,ppid,command
docker compose ps
```

Why:

- The first non-elevated `curl` calls could not connect from the sandboxed command environment.
- `lsof` confirmed Java was listening on `*:8081`.
- `docker compose ps` confirmed PostgreSQL was healthy.

Result:

- The backend was running and listening.
- The local HTTP calls needed elevated command permission in this environment.
- `ps` was blocked by sandbox permissions, but it was not needed after `lsof` proved the port listener.

Interview language:

> I verified the server was actually listening before blaming the application. The issue was command sandbox access, not Spring Boot.

### Verify Read Endpoints

```bash
curl -sS -i http://127.0.0.1:8081/api/v1/info
curl -sS -i http://127.0.0.1:8081/actuator/health
curl -sS -i http://127.0.0.1:8081/api/v1/records
```

Why:

- Confirms the API is reachable.
- Confirms the health endpoint reports database connectivity.
- Confirms the records endpoint returns an empty list before creating data.

Result:

- `GET /api/v1/info` returned `200`.
- `GET /actuator/health` returned `200` with overall status `UP` and database status `UP`.
- `GET /api/v1/records` returned `200` and `[]`.

Interview language:

> I checked health and read endpoints before writes so I knew the server and database were ready.

### Create A Retention Policy Through HTTP

```bash
curl -sS -i -X POST http://127.0.0.1:8081/api/v1/retention-policies -H 'Content-Type: application/json' -d '{"name":"Manual API Policy","description":"Policy created during manual API verification.","retentionPeriodDays":365}'
```

Why:

- Creates a real retention policy through the API.
- Gives the records API a real `retentionPolicyId` to use.

Result:

- Returned HTTP `201 Created`.
- Created retention policy ID: `2e971848-1277-4799-9df2-eb7dac4c91e2`.

Interview language:

> I created related data through the public API so the records test used realistic workflow steps.

### Create A Record Through HTTP

```bash
curl -sS -i -X POST http://127.0.0.1:8081/api/v1/records -H 'Content-Type: application/json' -d '{"externalId":"REC-MANUAL-001","name":"Manual API Record","retentionPolicyId":"2e971848-1277-4799-9df2-eb7dac4c91e2"}'
```

Why:

- Sends real JSON into the records controller.
- Verifies controller, validation, service, repository, JPA, and PostgreSQL together.

Result:

- Returned HTTP `201 Created`.
- Response included:
  - `externalId`: `REC-MANUAL-001`
  - `name`: `Manual API Record`
  - `status`: `ACTIVE`
  - `retentionPolicyId`: `2e971848-1277-4799-9df2-eb7dac4c91e2`

Interview language:

> I created a record through HTTP to verify the full request path from client JSON to database persistence.

### Verify List And Error Responses

```bash
curl -sS -i http://127.0.0.1:8081/api/v1/records
curl -sS -i -X POST http://127.0.0.1:8081/api/v1/records -H 'Content-Type: application/json' -d '{"externalId":"REC-MANUAL-001","name":"Duplicate Manual API Record"}'
curl -sS -i -X POST http://127.0.0.1:8081/api/v1/records -H 'Content-Type: application/json' -d '{"externalId":"","name":""}'
curl -sS -i -X POST http://127.0.0.1:8081/api/v1/records -H 'Content-Type: application/json' -d '{"externalId":"REC-MANUAL-404","name":"Missing Policy Manual Record","retentionPolicyId":"11111111-1111-1111-1111-111111111111"}'
```

Why:

- Verifies successful reads.
- Verifies duplicate business-rule error.
- Verifies request validation error.
- Verifies missing related-resource error.

Result:

- `GET /api/v1/records` returned `200` with one record.
- Duplicate external ID returned `409 Conflict`.
- Blank `externalId` and `name` returned `400 Bad Request`.
- Missing retention policy ID returned `404 Not Found`.

Interview language:

> I verified both happy path and important failure paths so the API contract is more than just a successful create.

### Verify Database Rows

```bash
docker compose exec -T postgres psql -U governance -d governance -c "select 'records' as table_name, count(*) as row_count from records union all select 'retention_policies', count(*) from retention_policies order by table_name;"
docker compose exec -T postgres psql -U governance -d governance -c "select r.external_id, r.name, r.status, p.name as retention_policy_name from records r left join retention_policies p on p.id = r.retention_policy_id order by r.external_id;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
```

Why:

- Confirms the HTTP create calls inserted rows into PostgreSQL.
- Confirms the record points to the expected retention policy.
- Confirms Flyway schema history is still valid.

Result:

- `records` row count: `1`.
- `retention_policies` row count: `1`.
- Record row:
  - `external_id`: `REC-MANUAL-001`
  - `name`: `Manual API Record`
  - `status`: `ACTIVE`
  - `retention_policy_name`: `Manual API Policy`
- Flyway version `1`, description `create records schema`, success `true`.

Interview language:

> I verified the database directly after HTTP calls so I knew the API did not just return JSON, it actually persisted data.

### Clean Up And Stop Services

```bash
docker compose exec -T postgres psql -U governance -d governance -c "delete from records; delete from retention_policies;"
kill 58525
lsof -nP -iTCP:8081 -sTCP:LISTEN
docker compose exec -T postgres psql -U governance -d governance -c "select 'records' as table_name, count(*) as row_count from records union all select 'retention_policies', count(*) from retention_policies order by table_name;"
docker compose ps
docker compose down
```

Why:

- Removes manual test rows.
- Stops the Spring Boot process that was running on port `8081`.
- Confirms port `8081` is no longer listening.
- Confirms the database is back to zero business rows.
- Stops PostgreSQL and removes the Compose network.

Result:

- Deleted `1` record and `1` retention policy.
- Port `8081` closed.
- `records` row count after cleanup: `0`.
- `retention_policies` row count after cleanup: `0`.
- Docker Compose stopped PostgreSQL and removed the network.

Interview language:

> I cleaned up after manual verification so the local environment stayed repeatable and did not leave services running.

### Final Checks And Commit

```bash
git diff --check
git status --short
git diff --stat
docker compose ps
git add docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git diff --cached --stat
git status --short
git add docs/command-log.md
git commit -m "docs: record manual records API verification"
```

Why:

- Confirms the command-log update has no whitespace problems.
- Confirms Docker Compose is stopped.
- Stages only the documentation for this manual verification phase.
- Reviews the staged documentation before committing.

Result:

- Whitespace check passed.
- Only `docs/command-log.md` changed.
- Docker Compose showed no running services.
- Staged files: `docs/command-log.md`.
- Staged whitespace check passed.

Interview language:

> I committed the manual verification notes separately because this phase changed documentation, not application behavior.

## Step 4.3: Add Records Pagination

This step changes `GET /api/v1/records` from returning one plain JSON array to returning one page of records plus pagination metadata.

### Official References Used

- Spring Data JPA paging and sorting: https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html
- Spring Data JPA sorting with `PageRequest` and `Sort`: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
- Spring MVC `@RequestParam`: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html

### Inspect The Current Records API

```bash
git status --short
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordService.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordController.java
sed -n '1,260p' backend/src/main/java/com/example/governance/records/GovernanceRecordResponse.java
sed -n '1,380p' backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java
sed -n '1,360p' backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java
sed -n '1,260p' backend/pom.xml
sed -n '1,260p' backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java
```

Why:

- Confirms the working tree is clean before code changes.
- Reads the current list endpoint and tests before changing the API response shape.
- Confirms Spring Data JPA is already available through Maven.
- Reads the shared exception handler because invalid pagination should return structured error JSON.

Result:

- The previous `GET /api/v1/records` returned a plain list.
- The service previously called `repository.findAll(Sort.by("externalId").ascending())`, which loads all rows.

Interview language:

> I inspected the existing list endpoint before changing it because pagination changes the API contract.

### Add Pagination Code

Files added:

- `backend/src/main/java/com/example/governance/api/PageResponse.java`
- `backend/src/main/java/com/example/governance/records/InvalidRecordPageRequestException.java`

Files updated:

- `backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordController.java`
- `backend/src/main/java/com/example/governance/records/GovernanceRecordService.java`

New response shape:

```json
{
  "content": [],
  "page": 0,
  "size": 20,
  "totalElements": 0,
  "totalPages": 0,
  "first": true,
  "last": true
}
```

Important code ideas:

```java
@RequestParam(defaultValue = "0") int page
@RequestParam(defaultValue = "20") int size
PageRequest.of(page, size, Sort.by("externalId").ascending())
repository.findAll(pageRequest)
PageResponse.from(records)
```

Why:

- `page` tells the API which chunk of records the client wants.
- `size` tells the API how many records to return in that chunk.
- `PageRequest` sends pagination and sorting instructions to Spring Data.
- `repository.findAll(pageRequest)` lets the database return one page instead of every row.
- `PageResponse` gives clients a stable JSON shape that the application owns.
- `InvalidRecordPageRequestException` turns invalid pagination into `400 Bad Request`.

Rules added:

- Page number must be `0` or greater.
- Page size must be between `1` and `100`.
- Records are still sorted by `externalId` ascending.

Interview language:

> I added pagination so the API can handle large record sets without loading and returning every row at once.

### Update Tests

Files updated:

- `backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java`
- `backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java`

Controller tests now verify:

- Default pagination: `GET /api/v1/records` uses `page=0` and `size=20`.
- Custom pagination: `GET /api/v1/records?page=1&size=1`.
- Response JSON includes `content`, `page`, `size`, `totalElements`, `totalPages`, `first`, and `last`.
- Invalid page number returns structured `400 Bad Request`.

Service tests now verify:

- The service builds `PageRequest.of(page, size, Sort.by("externalId").ascending())`.
- The repository receives the expected page request.
- Negative page numbers are rejected.
- Page size `0` is rejected.
- Page size above `100` is rejected.

Interview language:

> I updated both service tests and controller tests because pagination affects business query behavior and the HTTP response contract.

### Run Focused Records Tests

```bash
cd backend
./mvnw -Dtest=GovernanceRecordServiceTests,GovernanceRecordControllerTests test
```

Why:

- Runs only the tests affected by pagination.
- Gives fast feedback before running the larger suite.

Result:

- Build succeeded.
- Tests run: 15.
- Failures: 0.
- Errors: 0.

Interview language:

> I first ran focused tests for the changed records API so pagination failures would be easy to diagnose.

### Run Fast Backend Tests

```bash
cd backend
./mvnw test
```

Why:

- Runs every fast `*Tests` class.
- Confirms the shared API response changes did not break other fast tests.

Result:

- Build succeeded.
- Tests run: 25.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran the fast suite after the focused tests to catch regressions outside the records feature.

### Run Full Verification With PostgreSQL

```bash
docker compose up -d postgres
docker compose exec postgres pg_isready -U governance -d governance
docker compose ps
cd backend
./mvnw verify
```

Why:

- Starts PostgreSQL for integration tests.
- Confirms the database is accepting connections.
- Runs fast tests with Surefire.
- Runs integration tests with Failsafe.

Result:

- PostgreSQL started successfully.
- `pg_isready` returned `accepting connections`.
- Surefire ran 25 fast tests.
- Failsafe ran 10 integration tests.
- Build succeeded.
- Failures: 0.
- Errors: 0.

Interview language:

> I ran full verification because pagination changes the API layer, and I still want the database-backed tests to pass before committing.

### Verify Database Cleanup

```bash
docker compose exec -T postgres psql -U governance -d governance -c "select 'records' as table_name, count(*) as row_count from records union all select 'retention_policies', count(*) from retention_policies order by table_name;"
docker compose exec -T postgres psql -U governance -d governance -c "select installed_rank, version, description, success from flyway_schema_history order by installed_rank;"
docker compose ps
docker compose down
```

Why:

- Confirms integration tests left no business rows behind.
- Confirms Flyway migration history is still valid.
- Stops local infrastructure after verification.

Result:

- `records` row count: `0`.
- `retention_policies` row count: `0`.
- Flyway version `1`, description `create records schema`, success `true`.
- PostgreSQL stopped cleanly.

Interview language:

> I checked database cleanup after integration tests so the test environment remains repeatable.

### Final Checks And Commit

```bash
git diff --check
docker compose ps
git status --short
git diff --stat
git add backend/src/main/java/com/example/governance/api/ApiExceptionHandler.java backend/src/main/java/com/example/governance/api/PageResponse.java backend/src/main/java/com/example/governance/records/GovernanceRecordController.java backend/src/main/java/com/example/governance/records/GovernanceRecordService.java backend/src/main/java/com/example/governance/records/InvalidRecordPageRequestException.java backend/src/test/java/com/example/governance/records/GovernanceRecordControllerTests.java backend/src/test/java/com/example/governance/records/GovernanceRecordServiceTests.java docs/command-log.md
git diff --cached --name-only
git diff --cached --check
git diff --cached --stat
git status --short
git add docs/command-log.md
git commit -m "feat: add records pagination"
```

Why:

- Confirms formatting is clean.
- Confirms Docker Compose is stopped.
- Stages only pagination files and the command log.
- Reviews staged files before committing.
- Creates a focused checkpoint for records pagination.

Result:

- Whitespace check passed.
- Docker Compose showed no running services.
- Git showed the expected pagination files.
- Staged files: 8.
- Staged whitespace check passed.

Interview language:

> I committed pagination separately so the API contract change is easy to review and explain.
