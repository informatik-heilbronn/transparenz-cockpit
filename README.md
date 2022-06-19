# Transparent Cockpit

Transparenzcockpit is a database for project report organization with a database based on MongoDB and an online UI based on Next.js.

## Basics

Find more
Information [here]("https://confluence-student.it.hs-heilbronn.de/display/LabSWTransparentCockpit/LabSW_21_WS+TransparenzCockpit")
on our Confluence Page

## Structure

This project is divided into two part the **frontend** and the **backend**.

## Prerequisites

- Docker & Docker-Compose (Docker Desktop)
- Java 11 or newer
- Node.js (optional)

## Build

### Build Frontend & Backend

```shell
gradle build
```

### [Build Frontend](documentation/frontend/frontend_setup.md)

### [Build Backend](documentation/backend/backend_build_run.md)




#### Test

```shell
gradle backend:test backend:checkstyleMain
```

#### Run

```shell
gradle backend:run
```