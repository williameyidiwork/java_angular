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
