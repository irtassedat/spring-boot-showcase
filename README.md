# Spring Boot REST API Showcase

A collection of Spring Boot and Java projects covering OOP, JPA, REST APIs, and layered architecture.
Built during the **Workintech Full-Stack Java Bootcamp** (2024).

## Projects

| # | Project | Topics | Highlights |
|---|---------|--------|------------|
| 1 | [Employee Management](01-employee-management/) | OOP, Inheritance, Enums | Company-Employee-Healthplan hierarchy with polymorphism |
| 2 | [Library System](02-library-system/) | Services, Streams, Enums, Authorization | Multi-layer architecture: service, model, UI layers; borrow/return with invoicing |
| 3 | [REST API Basics](03-rest-api-basics/) | Spring Boot, REST Controller, CRUD | In-memory CRUD with `@RestController`, 5 endpoints |
| 4 | [SQL & JPA Queries](04-sql-jpa-queries/) | Spring Data JPA, Native Queries, Joins | 10 custom `@Query` methods with INNER/LEFT JOIN, GROUP BY, aggregation |
| 5 | [Plant Catalog API](05-plant-catalog-api/) | JPA, Validation, Exception Handling | Full REST API with `@ControllerAdvice`, bean validation, service layer, 6 endpoints |
| 6 | [Banking API](06-banking-api/) | JPA Relations, DTO Pattern | `@OneToOne`, `@OneToMany` with Customer-Account-Address; 3 controllers, 13 endpoints |
| 7 | [Movie-Actor API](07-movie-actor-api/) | ManyToMany, JoinTable, CRUD | `@ManyToMany` with join table, global exception handler, 6 endpoints |

## Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Lombok
- Jakarta Validation
- JUnit 5

## Running a Project

```bash
cd 05-plant-catalog-api
# Set DB password (or use default 'postgres')
export DB_PASSWORD=yourpassword
mvn spring-boot:run
```

> Projects 01 and 02 are plain Java (no Spring Boot) -- run `Main.java` directly.

## Structure

```
spring-boot-showcase/
├── 01-employee-management/    # OOP fundamentals
├── 02-library-system/         # Service-layer architecture
├── 03-rest-api-basics/        # First Spring Boot REST API
├── 04-sql-jpa-queries/        # Native SQL queries via JPA
├── 05-plant-catalog-api/      # Validation + Exception handling
├── 06-banking-api/            # JPA entity relationships
└── 07-movie-actor-api/        # ManyToMany relationships
```
