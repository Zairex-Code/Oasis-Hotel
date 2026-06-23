# Oasis Hotels API

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)

**Oasis Hotels** is an enterprise RESTful backend engine designed for the comprehensive management of hotel inventories, complex booking workflows, and critical real-time automations. 

Unlike a traditional CRUD application, this system incorporates advanced transactional logic for cross-date handling, a dynamic search engine based on multiple criteria, role-based segregation of duties via cryptographic security, and an automated state control system for orphaned or unpaid reservations.

## 🚀 Key Features

*   **🔒 Role-Based Access Control (RBAC):** Secure API endpoints using Spring Security and JWT. Roles include `CUSTOMER`, `HOTEL_MANAGER`, and `ADMIN`, with strict segregation of duties.
*   **🔍 Dynamic Search Engine:** Advanced search capabilities using Spring Data JPA's Criteria API. Filter hotels by city, price range, room type, and amenities dynamically.
*   **⚙️ Automated State Machine:** Reservations follow a strict lifecycle (`PENDING` -> `CONFIRMED` -> `COMPLETED` or `CANCELLED`). Background Cron Jobs automatically cancel unpaid reservations after 24 hours.
*   **⭐ Verified Review System:** Prevents rating fraud by ensuring users can only review hotels where they have a `COMPLETED` reservation. Automatically recalculates hotel ratings using transactional triggers.
*   **📧 Integrations:** Supports file uploads for room images and asynchronous email notifications for confirmed reservations.

## 🛠️ Technology Stack

*   **Language:** Java 21
*   **Framework:** Spring Boot 4.0.6
*   **Security:** Spring Security 6+ with JWT (JJWT) & BCrypt
*   **Database:** MySQL 8.0 (Dockerized)
*   **ORM:** Spring Data JPA / Hibernate
*   **Mapping:** MapStruct 1.6.3
*   **API Documentation:** Springdoc OpenAPI v3 (Swagger UI)
*   **Build Tool:** Gradle

## 📂 Project Architecture (N-Tier)

The application follows a clean N-Tier architecture to ensure maintainability and separation of concerns:

```text
src/main/java/com/oasis_hotels/api/
├── config        # Global configurations (Security, Swagger, WebMvc)
├── controller    # REST Controllers (API Endpoints)
├── dto           # Data Transfer Objects for request/response payloads
├── entity        # Domain Entities mapped to the database
├── exception     # Global Error Handling & Validation
├── mapper        # MapStruct interfaces for Entity-DTO mapping
├── repository    # Data Access Layer & JPA Specifications
├── scheduler     # Background Cron Jobs (e.g., BookingCancellationScheduler)
└── service       # Business Logic Layer (Interfaces & Implementations)
```

## 🐳 Getting Started

### Prerequisites
*   [Java 21](https://jdk.java.net/21/) installed
*   [Docker](https://www.docker.com/) and Docker Compose installed

### 1. Start the Database
The project includes a `docker-compose.yml` file to quickly spin up a MySQL database isolated from your host system.

```bash
docker-compose up -d
```
*Note: The database runs on host port `3308` to avoid conflicts with local MySQL instances.*

### 2. Build and Run the Application
You can use the Gradle Wrapper included in the project to run the application without installing Gradle globally.

```bash
# Build the project
./gradlew build

# Run the Spring Boot application
./gradlew bootRun
```

### 3. API Documentation
Once the application is running, you can interact with the API endpoints through the integrated Swagger UI:

*   **Swagger UI:** `http://localhost:8080/swagger-ui.html`
*   **OpenAPI Specs:** `http://localhost:8080/v3/api-docs`

*(Ensure you have registered a user and authenticated via the `/auth` endpoints to receive a JWT for secured routes).*

## 🗄️ Database Schema Overview

*   **User:** Credentials and identity data (`1:N` with Reservation and Review).
*   **Hotel:** The physical establishment and consolidated rating (`1:N` with Room and Review).
*   **Room:** Hotel rooms with specific types and prices (`N:1` with Hotel, `1:N` with Reservation).
*   **Reservation:** Transactional document managing dates, states, and amounts (`N:1` with User and Room).
*   **Review:** User comments and 1-5 star ratings (`N:1` with User and Hotel).
