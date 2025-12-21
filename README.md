
# 🚀 Spring Boot Hands‑On Mastery – Order Management System (OMS)

This repository is a **complete beginner‑to‑advanced hands‑on guide** for mastering **Spring Boot 3.x** by building a **real‑world Order Management System (OMS)**.

👉 One project.  
👉 Step‑by‑step.  
👉 Production‑ready mindset.

---

## 🧠 Target Audience
- Beginners learning Spring Boot
- Java developers moving to Microservices
- Backend interview preparation
- Engineers who prefer **hands‑on learning**

---

## 🛠 Tech Stack
- Java 17
- Spring Boot 3.x
- Maven
- PostgreSQL
- Redis
- Kafka
- Docker & Docker Compose
- Testcontainers
- OpenAPI (Swagger)
- OAuth2 / JWT
- Micrometer + Prometheus
- Resilience4j

---

## 📦 Project Theme
**Order Management System (OMS)**

We continuously evolve a **single project** instead of building multiple demos.

---

# 🧩 PHASE‑WISE HANDS‑ON GUIDE

---

## 🔹 PHASE 1 – Spring Boot Foundations

### 🎯 Objective
Understand Spring Boot fundamentals and application startup lifecycle.

---

### 🪜 Step‑by‑Step Instructions

### Step 1: Create Spring Boot Project
Use **Spring Initializr** (Web or IDE):

- Project: **Maven**
- Language: **Java**
- Spring Boot: **3.x**
- Java: **17**
- Dependencies:
  - Spring Web
  - Validation

---

### Step 2: Project Structure Overview
Understand generated structure:

```
src/main/java
 └── com.vishtech.oms
     └── OmsServiceApplication.java
```

Key concepts:
- `@SpringBootApplication`
- Auto‑configuration
- Component scanning

---

### Step 3: Create First REST Controller
Create `HealthController`:

```java
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String health() {
        return "OMS Service is UP";
    }
}
```

---

### Step 4: Run Application
```bash
mvn spring-boot:run
```

Verify:
```
http://localhost:8080/health
```

---

### Step 5: Understand Boot Lifecycle
Learn:
- Embedded Tomcat
- DispatcherServlet
- Request → Controller → Response flow

✅ **Outcome:**  
You understand how Spring Boot applications start and serve HTTP requests.

---

## 🔹 PHASE 2 – REST APIs, Validation & Testing

### 🎯 Objective
Build professional REST APIs with validation and testing.

---

### 🪜 Step‑by‑Step Instructions

### Step 1: Create Domain Model
- `OrderEntity`
- `OrderStatus` enum

---

### Step 2: Create DTOs
- `OrderRequestDto`
- `OrderResponseDto`

Apply validation:
```java
@NotBlank
@NotNull
@Min(1)
```

---

### Step 3: Build REST API
Endpoints:
- `POST /api/orders`
- `GET /api/orders/{id}`

---

### Step 4: Service Layer
- `OrderService`
- `OrderServiceImpl`

Responsibilities:
- Business logic
- No validation re‑checks

---

### Step 5: Global Exception Handling
Create `GlobalExceptionHandler`:
- Validation errors
- Business exceptions

---

### Step 6: Testing
- Controller tests → `@WebMvcTest`
- Repository tests → `@DataJpaTest`
- Use mocks properly
- Avoid deprecated `@MockBean`

✅ **Outcome:**  
You can write clean, testable REST APIs.

---

## 🔹 PHASE 3 – Configuration & Profiles

### 🎯 Objective
Master environment‑specific configuration.

---

### 🪜 Step‑by‑Step Instructions

### Step 1: Profiles
Create:
- `application-dev.yml`
- `application-test.yml`
- `application-prod.yml`

---

### Step 2: Configure Databases
- Dev → Local PostgreSQL
- Test → Testcontainers
- Prod → External DB

---

### Step 3: Activate Profiles
- `@ActiveProfiles`
- `spring.profiles.active`

---

### Step 4: Handle TimeZone Issues
```properties
spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Kolkata
```

---

### Step 5: Disable Unwanted Features in Tests
- Tracing
- Observability

✅ **Outcome:**  
You understand enterprise‑grade configuration management.

---

## 🔹 PHASE 4 – JPA & Transactions

### 🎯 Objective
Deep dive into persistence.

### Steps
- Pagination & sorting
- Custom queries
- Transactions
- Optimistic locking
- Auditing fields

---

## 🔹 PHASE 5 – Redis Caching

### 🎯 Objective
Improve performance.

### Steps
- Redis integration
- `@Cacheable`
- TTL strategies

---

## 🔹 PHASE 6 – Kafka Messaging

### 🎯 Objective
Event‑driven architecture.

### Steps
- Kafka Producer
- Kafka Consumer
- Order events
- Retry handling

---

## 🔹 PHASE 7 – Security (OAuth2 + JWT)

### 🎯 Objective
Secure APIs.

### Steps
- JWT tokens
- Role‑based access
- Secured endpoints

---

## 🔹 PHASE 8 – Observability

### 🎯 Objective
Production readiness.

### Steps
- Micrometer metrics
- Prometheus
- Custom metrics

---

## 🔹 PHASE 9 – Resilience

### 🎯 Objective
Fault tolerance.

### Steps
- Circuit breaker
- Retry
- Rate limiting

---

## 🧪 Running Tests
```bash
mvn test
```

Uses **Testcontainers** (Docker required).

---

## 🐳 Run Full Stack
```bash
docker-compose up
```

---

## 🏁 Final Outcome
By completing all phases you will:
- Build real Spring Boot apps
- Understand microservices deeply
- Be interview‑ready 🚀

---

⭐ Star this repo if it helped you!
