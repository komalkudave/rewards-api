# Rewards API

## Overview

This project is a Spring Boot REST API that calculates reward points for customers based on their transactions over a
three-month period.

## Reward Calculation Rules

- 2 points for every dollar spent over $100
- 1 point for every dollar spent between $50 and $100

### Example

Transaction amount: $120

- 2 × 20 = 40
- 1 × 50 = 50

Total reward points = 90

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito
- Lombok

---

## Project Structure

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