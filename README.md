
# 🚀 Spring Boot Hands‑On Mastery – Order Management System (OMS)

This repository is a **step‑by‑step hands‑on learning journey** for **beginners to intermediate developers** who want to master **Spring Boot 3.x** by building a **real‑world Order Management System (OMS)**.

You will not learn concepts in isolation.
👉 You will **apply every concept directly in code**.

---

## 🧠 Who is this for?
- Beginners in Spring Boot / Microservices
- Java developers moving to Spring Boot 3.x
- Developers preparing for backend interviews
- Anyone who learns best by **hands‑on practice**

---

## 🛠 Tech Stack
- Java 17
- Spring Boot 3.x
- Maven
- PostgreSQL
- Redis
- Kafka
- Docker
- Testcontainers
- OpenAPI (Swagger)
- OAuth2 / JWT
- Micrometer + Prometheus
- Resilience4j

---

## 📦 Project Theme
**Order Management System (OMS)**

Features grow gradually:
- Create Orders
- Persist Orders
- Validation & Exception Handling
- Profiles & Configuration
- Caching
- Messaging
- Security
- Observability
- Resilience

---

# 📚 Learning Phases

---

## 🔹 PHASE 1 – Spring Boot Foundations
### Goal: Understand Spring Boot basics

### Hands‑On Steps
1. Create Spring Boot project using Maven
2. Understand:
   - `@SpringBootApplication`
   - Auto‑configuration
   - Component scanning
3. Create first REST controller
4. Run and test using browser / curl

---

## 🔹 PHASE 2 – REST API, Validation & Testing
### Goal: Build real REST APIs correctly

### Day‑wise Hands‑On
- Create Order API (`POST /orders`)
- DTOs & Entities
- Bean Validation (`@NotBlank`, `@Min`, etc.)
- Global Exception Handling
- Unit Tests
  - Controller tests (`@WebMvcTest`)
  - Repository tests (`@DataJpaTest`)
- Testcontainers with PostgreSQL
- Handling Docker‑less environments

📌 Key Learning:
- Why validation annotations are enough
- Why we avoid re‑validation in service layer
- How Spring test slices work

---

## 🔹 PHASE 3 – Configuration & Profiles
### Goal: Master real‑world configuration

### Covered Topics
- `application.yml` vs `application.properties`
- Profiles:
  - `dev`
  - `test`
  - `prod`
- `@ActiveProfiles`
- `@DynamicPropertySource`
- Externalized configuration
- Secure secrets handling
- TimeZone issues with PostgreSQL
- Conditional beans
- Profile‑specific beans

### Hands‑On
- Configure DB per environment
- Test profile using Testcontainers
- Disable tracing & observability in tests

---

## 🔹 PHASE 4 – Database, JPA & Transactions
### Goal: Become confident with persistence

### Hands‑On
- Spring Data JPA
- Custom queries
- Pagination & sorting
- Transaction management
- Optimistic locking
- Auditing (createdAt, updatedAt)

---

## 🔹 PHASE 5 – Caching with Redis
### Goal: Improve performance

### Hands‑On
- Redis integration
- `@Cacheable`, `@CacheEvict`
- Cache strategies
- TTL management

---

## 🔹 PHASE 6 – Messaging with Kafka
### Goal: Event‑driven architecture

### Hands‑On
- Kafka Producer & Consumer
- Order Created events
- Idempotency
- Error handling

---

## 🔹 PHASE 7 – Security
### Goal: Secure APIs

### Hands‑On
- OAuth2
- JWT authentication
- Role‑based access
- Securing endpoints

---

## 🔹 PHASE 8 – Observability
### Goal: Production readiness

### Hands‑On
- Micrometer metrics
- Prometheus integration
- Custom metrics
- Logging best practices
- Tracing basics

---

## 🔹 PHASE 9 – Resilience
### Goal: Build fault‑tolerant systems

### Hands‑On
- Resilience4j
- Circuit Breaker
- Retry
- Rate Limiting
- Bulkhead

---

## 🧪 Running Tests
```bash
mvn test
```

📌 Tests use **Testcontainers**
- PostgreSQL runs in Docker
- Tests auto‑skip if Docker is unavailable

---

## 🐳 Running with Docker
```bash
docker-compose up
```

---

## 📖 How to Learn from this Repo
1. Start from **Phase 1**
2. Checkout commits phase‑by‑phase
3. Read code + comments
4. Run tests
5. Break things and fix them 😄

---

## ⭐ Best Practices Followed
- Clean architecture
- Layered design
- Proper testing
- Production‑ready configuration
- Interview‑oriented explanations

---

## 🤝 Contribution
This repo is designed for **learning**.
Feel free to fork and extend.

---

## 🏁 Final Note
If you complete all phases **hands‑on**, you will:
- Understand Spring Boot deeply
- Be confident in real projects
- Crack backend interviews easily 🚀

Happy Coding! 🎉
