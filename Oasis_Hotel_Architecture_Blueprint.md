# Oasis Hotels API - Architectural Blueprint & Project Specification

This document serves as the official **Requirements Analysis, Functional Specification, and Architecture Design** for the development of the **Oasis Hotels API**. This file provides all the necessary context, detailed business rules, and the package structure that the system must follow to ensure scalability, security, and maintainability.

---

## 📋 1. Project Overview

**Oasis Hotels** is an enterprise RESTful backend engine designed for the comprehensive management of hotel inventories, complex booking workflows, and critical real-time automations. Unlike a traditional CRUD application, this system incorporates advanced transactional logic for cross-date handling, a dynamic search engine based on multiple criteria, role-based segregation of duties via cryptographic security, and an automated state control system for orphaned or unpaid reservations.

---

## 🛠️ 2. Production Technology Stack

To guarantee a professional standard ready for corporate environments, the project will be built on the following technologies:

* **Language:** Java 17+ (Leveraging *Records*, *Text Blocks*, and *Switch Expressions*).
* **Base Framework:** Spring Boot 4.x (or the latest stable extended support 3.x branch).
* **Security:** Spring Security 6+ with JSON Web Tokens (**JWT**) and password encryption using **BCrypt**.
* **Data Persistence:** Spring Data JPA with **Hibernate 6+** as the native ORM provider.
* **Database:** **MySQL 8.0** mounted on an isolated development container.
* **Object Mapping:** **MapStruct 1.5+** for optimized in-memory transfer from Entities to DTOs.
* **Validation:** **Hibernate Validator (Jakarta Validation)** for data corruption containment at the entry point.
* **Task Automation:** **Spring Task Scheduling** for asynchronous processes and background Cron Jobs.
* **Documentation:** **Spring doc OpenAPI v3 (Swagger UI)** for interactive HTTP contract exposure.
* **Containers:** **Docker & Docker Compose** for database environment consistency.

---

## 🧩 3. Approved Modules Analysis and Business Rules

The system is divided into **5 interconnected macro-modules**, each designed to address a specific pillar of the enterprise architecture.

### 👥 Module I: Role-Based Access Control (RBAC) and Cryptographic Security
Access to the API resources is strictly regulated by user identity. Direct manipulation of records without validated tokens in the HTTP Header (`Authorization: Bearer <JWT>`) is not permitted.

* **Defined Roles:**
    1. `CUSTOMER`: End clients. They can register publicly, search for hotels, view available rooms, manage their own reservations, and interact with their reviews.
    2. `HOTEL_MANAGER`: Hotel administrative staff. They can create rooms, alter prices, manage the inventory of their assigned hotel, and audit reservations for their rooms. They cannot create new hotels in the global system.
    3. `ADMIN`: System superuser. Has total control over the ecosystem. It is the only role authorized to create new hotels, assign managers (`HOTEL_MANAGER`) to those establishments, and perform moderation actions (banning users or deleting malicious reviews).
* **Mandatory Security:**
    * Passwords are stored using the `BCryptPasswordEncoder` algorithm. They never travel or are exposed in plain text.
    * The API uses a stateless configuration through filter interceptors (`OncePerRequestFilter`) to decode and validate the signature of JWT tokens on every request.

### 🔍 Module II: Dynamic Search Engine (Spring Data JPA Specifications)
Filtering is not done using static queries (`findBy...`). JPA's **Criteria API** is implemented to build dynamic SQL statements at runtime.

* **Supported Search Parameters (Optional and Combinable):**
    * `city` (String): Match on hotel location.
    * `minPrice` / `maxPrice` (BigDecimal): Strict price range per night.
    * `roomType` (Enum: SINGLE, DOUBLE, SUITE, FAMILY): Filtering by type.
    * `amenities` (List<String>): Filtering by amenities (WiFi, Pool, Parking, AC).

### ⚙️ Module III: State Machine and Scheduled Tasks (Cron Jobs)
Reservations possess a life cycle controlled by a strict state machine. The permitted flow is:
`PENDING` -> `CONFIRMED` -> `COMPLETED` or `CANCELLED`.

* **Critical Business Rule:** When a reservation is created, it enters the `PENDING` state and blocks the room. The user has **24 hours to simulate payment**.
* **The Cron Job:** Using `@Scheduled`, an automatic process sweeps the database searching for `PENDING` reservations older than 24 hours, changes them to `CANCELLED`, and releases the inventory.

### ⭐ Module IV: Review System Linked to Real Reservations
To prevent rating fraud on the platform, the database validates the source of the comment.

* **Validation Rules:**
    1. A `User` can only submit a `Review` if they have a `Reservation` at that `Hotel`.
    2. The reservation must be in the `COMPLETED` state.
    3. **Automated Recalculation:** Upon adding a review, a trigger calculates the exact average and updates the `Hotel`'s `rating` field in a single transaction (`@Transactional`).

### 📧 Module V: Third-Party Services Integration
* **Files (`MultipartFile`):** Uploading real room images, validating their format (JPEG/PNG) and saving the generated static path in the database.
* **Messaging (`JavaMailSender`):** Upon confirming a reservation, an asynchronous thread (`@Async`) is triggered to send an email to the client.

---

## 🗄️ 4. Data Model and Relationships

| Entity | Description | Key Relationships |
| :--- | :--- | :--- |
| **User** | Credentials and identity data. | `1:N` with *Reservation* and *Review* |
| **Hotel** | The physical establishment and its consolidated rating. | `1:N` with *Room* and *Review* |
| **Room** | Hotel rooms (price, type). | `N:1` with *Hotel*, `1:N` with *Reservation* |
| **Reservation**| Transactional document (dates, amounts, states). | `N:1` with *User* and *Room* |
| **Review** | Comments and rating (1-5 stars). | `N:1` with *User* and *Hotel* |

---

## 📂 5. Package Structure (N-Tier Architecture)

```text
com.oasis_hotels.api
│
├── config                      # Configuraciones globales (Security, Swagger, WebMvc)
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
│
├── controller                  # Capa Web / Controladores REST (Exposición de Endpoints)
│   ├── auth                    # Controladores para login y registro público
│   ├── HotelController.java
│   ├── RoomController.java
│   ├── ReservationController.java
│   └── ReviewController.java
│
├── dto                         # Objetos de Transferencia de Datos (Data Transfer Objects)
│   ├── auth
│   │   ├── LoginRequest.java
│   │   └── AuthResponse.java
│   ├── hotel
│   │   ├── HotelRequestDTO.java
│   │   └── HotelResponseDTO.java
│   ├── room
│   └── reservation
│
├── entity                      # Entidades del Dominio (Mapeo directo a tablas SQL)
│   ├── enums                   # Enumeraciones del sistema (Roles, RoomType, BookingStatus)
│   ├── User.java
│   ├── Hotel.java
│   ├── Room.java
│   ├── Reservation.java
│   └── Review.java
│
├── exception                   # Manejo de Errores Globales (Escudo de Validación)
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── InvalidBookingException.java
│
├── mapper                      # Interfaces de MapStruct para transformación Entity/DTO
│   ├── HotelMapper.java
│   ├── RoomMapper.java
│   └── ReservationMapper.java
│
├── repository                  # Capa de Acceso a Datos (Spring Data JPA / Specifications)
│   ├── UserRepository.java
│   ├── HotelRepository.java
│   ├── RoomRepository.java
│   ├── ReservationRepository.java
│   └── spec                    # Clases de especificación para Criteria API (Filtros dinámicos)
│       └── HotelSpecifications.java
│
├── scheduler                   # Tareas programadas en segundo plano (Cron Jobs)
│   └── BookingCancellationScheduler.java
│
└── service                     # Capa de Lógica de Negocio (Interfaces e Implementaciones)
    ├── HotelService.java
    ├── ReservationService.java
    ├── UserService.java
    └── impl
        ├── HotelServiceImpl.java
        └── ReservationServiceImpl.java