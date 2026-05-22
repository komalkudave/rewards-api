# Rewards API

## Overview

Rewards API is a Spring Boot RESTful web service that calculates reward points earned by customers based on their
purchase transactions during the last three months.

The application provides:

- Monthly reward points for each customer
- Total reward points for each customer
- RESTful API endpoints
- Exception handling
- Swagger API documentation
- Unit and integration testing

This project is designed using layered architecture and follows Java/Spring Boot coding standards to simulate a
real-world backend application.

---

# Business Requirement

A retailer offers reward points to customers based on purchase amount.

## Reward Rules

- 2 points for every dollar spent above $100
- 1 point for every dollar spent between $50 and $100

### Example

Transaction Amount = $120

Reward Calculation:

- (120 - 100) × 2 = 40 points
- (100 - 50) × 1 = 50 points

Total Reward Points = 90

Rewards are calculated only for transactions performed during the last three months.

---

# Technologies Used

| Technology      | Purpose                         |
|-----------------|---------------------------------|
| Java 17         | Core programming language       |
| Spring Boot     | REST API development            |
| Spring Data JPA | Database interaction            |
| H2 Database     | In-memory database              |
| Maven           | Build and dependency management |
| Lombok          | Reduces boilerplate code        |
| Swagger OpenAPI | API documentation and testing   |
| JUnit 5         | Unit testing                    |
| Mockito         | Mocking framework               |

---

# Architecture

The application follows layered architecture:

Controller → Service → Repository → Database

## Layer Responsibilities

### Controller Layer

Handles incoming HTTP requests and API responses.

### Service Layer

Contains business logic for reward calculation.

### Repository Layer

Handles database interaction using Spring Data JPA.

### DTO Layer

Transfers API request and response data.

### Exception Layer

Provides centralized exception handling.

### Utility Layer

Contains reusable reward calculation utility logic.

---

# Project Structure

```text
src/main/java/com/rewards/rewards_api
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── util
└── config
```

---

# Reward Calculation Logic

## Scenario 1

If transaction amount is less than or equal to $50:

```text
0 reward points
```

---

## Scenario 2

If transaction amount is between $50 and $100:

```text
1 point for every dollar above $50
```

### Example

```text
Amount = 75
Points = 75 - 50 = 25
```

---

## Scenario 3

If transaction amount is above $100:

```text
2 points for every dollar above $100
+
1 point for every dollar between $50 and $100
```

### Example

```text
Amount = 120

(120 - 100) × 2 = 40
(100 - 50) × 1 = 50

Total = 90 points
```

---

# Key Features

- Dynamic last 3 months reward calculation
- Monthly and total reward aggregation
- Layered architecture implementation
- Global exception handling
- SQL-based database initialization
- Swagger API documentation
- Unit and integration testing
- JPA entity relationships
- Validation handling
- Logging support

---

# API Endpoints

| Method | Endpoint                              | Description                           |
|--------|---------------------------------------|---------------------------------------|
| GET    | `/api/rewards/customers`              | Fetch rewards for all customers       |
| GET    | `/api/rewards/customers/{customerId}` | Fetch rewards for a specific customer |

---

# Sample API Response

```json
[
  {
    "customerId": 1,
    "customerName": "John",
    "monthlyRewards": [
      {
        "month": "March 2026",
        "points": 90
      },
      {
        "month": "April 2026",
        "points": 25
      }
    ],
    "totalRewards": 115
  }
]
```

---

# Swagger API Documentation

Swagger UI is enabled for API testing and documentation.

## Swagger URL

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Database Initialization

Database initialization is performed using SQL scripts.

## SQL File Location

```text
src/main/resources/util/schema.sql
src/main/resources/util/data.sql
```

## Why SQL Initialization?

SQL scripts were used instead of Java loader classes because:

- closer to enterprise application design
- easier maintenance
- cleaner separation of concerns
- better database initialization management

---

# Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

Handled Exceptions:

- ResourceNotFoundException
- IllegalArgumentException
- Validation exceptions
- Generic exceptions

Standardized API error responses are used throughout the application.

---

# Validation

Validation annotations are implemented using:

- `@NotBlank`
- `@NotNull`
- `@Positive`

This ensures invalid requests are rejected with proper validation messages.

---

# Logging

Logging is implemented using Lombok `@Slf4j`.

Logs are added for:

- API execution flow
- Customer retrieval
- Exception scenarios

This improves debugging and monitoring.

---

# Testing

The project includes both unit and integration tests.

## Unit Tests

Implemented for:

- Reward calculation utility
- Service layer logic
- Exception scenarios

## Integration Tests

Implemented for:

- REST API endpoint testing
- Controller testing

## Testing Frameworks Used

- JUnit 5
- Mockito
- Spring Boot Test

---

# Design Decisions

The following design decisions were implemented to improve maintainability and code quality:

- Used layered architecture for separation of concerns
- Used DTOs to avoid exposing entities directly
- Used `YearMonth` and `TreeMap` for sorted monthly rewards
- Used centralized exception handling
- Used JPA entity relationships between Customer and Transaction
- Used reusable utility class for reward calculation
- Used SQL scripts for database initialization
- Added Swagger for API documentation
- Added logging for monitoring and debugging

---

# Assumptions

- Rewards are calculated only for transactions from the last 3 months
- Negative transaction amounts are invalid
- Customer IDs are unique
- Reward points are rounded down to integer values
- H2 database is used for development/testing

---

# How To Run The Project

## Clone Repository

```bash
git clone <github-repository-url>
```

## Navigate To Project

```bash
cd rewards-api
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

---

# Future Enhancements

Possible future improvements:

- Add authentication and authorization
- Add pagination and filtering
- Add Docker support
- Add Flyway/Liquibase database migrations
- Use PostgreSQL/MySQL database
- Add API versioning
- Deploy application to cloud platform

---

# Conclusion

This project demonstrates:

- REST API development using Spring Boot
- Layered architecture implementation
- Exception handling
- Validation handling
- Unit and integration testing
- Clean coding practices
- Real-world backend application structure