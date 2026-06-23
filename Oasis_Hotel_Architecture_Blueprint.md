# Oasis Hotels API - Architectural Blueprint & Project Specification

This document serves as the official **Requirements Analysis, Functional Specification, and Architecture Design** for the development of the **Oasis Hotels API**. This file provides all the necessary context, detailed business rules, and the package structure that the system currently follows to ensure scalability, security, and maintainability.

---

## 📋 1. Project Overview

**Oasis Hotels** is a RESTful backend engine designed for the management of hotel inventories and booking workflows. The system incorporates role-based segregation of duties via cryptographic security, and a state control system for reservations and room availability.

---

## 🛠️ 2. Production Technology Stack

* **Language:** Java 21
* **Base Framework:** Spring Boot 4.x (currently specified in build.gradle)
* **Security:** Spring Security 6+ with JSON Web Tokens (**JWT**) and password encryption using **BCrypt**.
* **Data Persistence:** Spring Data JPA with **Hibernate** as the native ORM provider.
* **Database:** **MySQL 8.0** mounted on an isolated development container.
* **Object Mapping:** **MapStruct 1.6.3** for optimized in-memory transfer from Entities to DTOs.
* **Validation:** **Hibernate Validator (Jakarta Validation)** for data corruption containment at the entry point.
* **Documentation:** **Springdoc OpenAPI v3 (Swagger UI)** for interactive HTTP contract exposure.
* **Containers:** **Docker & Docker Compose** for database environment consistency.

---

## 🧩 3. Active Modules Analysis and Business Rules

The system is currently divided into **core interconnected modules**.

### 👥 Module I: Role-Based Access Control (RBAC) and Cryptographic Security
Access to the API resources is strictly regulated by user identity. Direct manipulation of records without validated tokens in the HTTP Header (`Authorization: Bearer <JWT>`) is not permitted.

* **Defined Roles:**
    1. `CUSTOMER`: End clients. They can register, search for hotels, view available rooms, and manage their own reservations.
    2. `HOTEL_MANAGER`: Hotel administrative staff. They manage the inventory of their assigned hotel, create/update rooms, and audit reservations.
    3. `ADMIN`: System superuser. Has total control over the ecosystem. It is the only role authorized to perform system-wide administrative actions and manage user roles.
* **Mandatory Security:**
    * Passwords are stored using the `BCryptPasswordEncoder` algorithm. They never travel or are exposed in plain text.
    * The API uses a stateless configuration through filter interceptors (`JwtAuthenticationFilter`) to decode and validate the signature of JWT tokens on every request.

### ⚙️ Module II: Core Entities and State Control
Reservations and Rooms possess a life cycle controlled by strict statuses.

* **Room Status (`RoomStatus`):**
    * `AVAILABLE`, `OCCUPIED`, `MAINTENANCE`
* **Reservation Status (`ReservationStatus`):**
    * `PENDING`, `CONFIRMED`, `CANCELLED`, `COMPLETED`
* **Hotel Status (`HotelStatus`):**
    * `ACTIVE`, `INACTIVE`

---

## 🗄️ 4. Data Model and Relationships

| Entity | Description | Key Relationships |
| :--- | :--- | :--- |
| **User** | Credentials, identity data, and system Role. | `1:N` with *Reservation* |
| **Hotel** | The physical establishment. | `1:N` with *Room* |
| **Room** | Hotel rooms (price, type, status). | `N:1` with *Hotel*, `1:N` with *Reservation* |
| **Reservation**| Transactional document (dates, status). | `N:1` with *User* and *Room* |

---

## 📂 5. Package Structure (N-Tier Architecture)

```text
com.oasis_hotel.oasis_hotel
│
├── config                      # Configuraciones globales (Security, Swagger, WebMvc, JWT)
│   ├── ApplicationConfig.java
│   ├── JwtAuthenticationFilter.java
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
│
├── controller                  # Capa Web / Controladores REST (Exposición de Endpoints)
│   ├── AuthController.java
│   ├── HotelController.java
│   ├── ReservationController.java
│   ├── RoomController.java
│   └── UserController.java
│
├── dto                         # Objetos de Transferencia de Datos (Data Transfer Objects)
│   ├── auth                    # Request/Response de Autenticación
│   ├── hotel                   # Request/Response de Hotel
│   ├── reservation             # Request/Response de Reserva
│   ├── room                    # Request/Response de Habitación
│   └── user                    # Request/Response de Usuario
│
├── entity                      # Entidades del Dominio (Mapeo directo a tablas SQL)
│   ├── enums                   # Enumeraciones del sistema (HotelStatus, ReservationStatus, Role, RoomStatus, RoomType)
│   ├── Hotel.java
│   ├── Reservation.java
│   ├── Room.java
│   └── User.java
│
├── exception                   # Manejo de Errores Globales (Escudo de Validación)
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── mapper                      # Interfaces de MapStruct para transformación Entity/DTO
│   ├── HotelMapper.java
│   ├── ReservationMapper.java
│   ├── RoomMapper.java
│   └── UserMapper.java
│
├── repository                  # Capa de Acceso a Datos (Spring Data JPA)
│   ├── HotelRepository.java
│   ├── ReservationRepository.java
│   ├── RoomRepository.java
│   └── UserRepository.java
│
└── service                     # Capa de Lógica de Negocio e Integración
    ├── HotelService.java
    ├── JwtService.java
    ├── ReservationService.java
    ├── RoomService.java
    ├── UserService.java
    └── impl                    # Implementación de los servicios
        ├── HotelServiceImpl.java
        ├── ReservationServiceImpl.java
        ├── RoomServiceImpl.java
        └── UserServiceImpl.java
```