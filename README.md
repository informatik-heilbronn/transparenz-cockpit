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

### Build Frontend

```shell
gradle frontend:build
```

#### Technologies

A list of technologies used within the project:
* Next.js(https://nextjs.org)
* MongoDB(https://www.mongodb.com)

#### Installation - Frontend

To run the web UI locally navigate to the frontend folder and run **npm install** followed by **npm run dev**.

### Build Backend

```shell
gradle backend:build
```

### Prerequisites

- Docker & Docker-Compose Install (Docker Desktop)
- Java 11 or newer

#### Requirements

To Start

```shell
docker-compose up -d
```

To Stop

```shell
docker-compose down
```

#### Test

```shell
gradle backend:test backend:checkstyleMain
```

#### Run

```shell
gradle backend:run
```