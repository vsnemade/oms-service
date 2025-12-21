
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

### 🎯 Phase Goal
Learn how to design **clean REST APIs**, apply **validation correctly**, handle **errors globally**, and write **proper unit & slice tests** using Spring Boot best practices.

By the end of this phase, you will:
- Build a real Order API
- Apply Bean Validation correctly
- Understand *where validation belongs*
- Write Controller & Repository tests confidently

---

## 🧩 Step-by-Step Hands-On Guide

---

### 🪜 Step 1: Define Order Domain Model

Create an enum for order status:

```java
public enum OrderStatus {
  CREATED,
  CONFIRMED,
  CANCELLED
}
```
Create the JPA Entity:
```java
@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;
}
```
📌 Why Entity ≠ DTO?<br/>
Entities represent DB structure. DTOs represent API contracts.

🪜 Step 2: Create Request & Response DTOs<br/>
OrderRequestDto (Input Validation Happens Here)
```java
public class OrderRequestDto {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;
}
```
📌 Key Rule

Validation belongs at API boundaries (Controller layer)

OrderResponseDto
```java
public class OrderResponseDto {
    private Long orderId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private OrderStatus status;
    private String message;
}
```
🪜 Step 3: Create Repository Layer <br/>
```java
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByProductName(String productName);
}
```
📌 Spring Data JPA automatically implements this interface.
🪜 Step 4: Create Service Layer
Service Interface<br/>
```java
🪜 Step 4: Create Service Layer
Service Interface
```
Service Implementation<br/>
```java
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {

        OrderEntity entity = OrderEntity.builder()
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        OrderEntity saved = repository.save(entity);

        return new OrderResponseDto(
                saved.getId(),
                saved.getProductName(),
                saved.getQuantity(),
                saved.getPrice(),
                saved.getStatus(),
                "Order created successfully"
        );
    }
}
```
📌 Important Concept

❌ Do NOT re-validate here

✅ Trust controller-level validation

🪜 Step 5: Create REST Controller<br/>
```java
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(request));
    }
}
```
📌 @Valid triggers validation before service execution.

🪜 Step 6: Global Exception Handling

Create GlobalExceptionHandler:
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(errors);
    }
}
```
📌 This avoids repeating try/catch blocks everywhere.
🧪 TESTING SECTION (MOST IMPORTANT)
🪜 Step 7: Controller Test (@WebMvcTest)<br/>
```java
@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateOrder() throws Exception {

        OrderRequestDto request =
                new OrderRequestDto("iPhone", 2, BigDecimal.valueOf(120000));

        OrderResponseDto response =
                new OrderResponseDto(1L, "iPhone", 2,
                        BigDecimal.valueOf(120000),
                        OrderStatus.CREATED, null);

        when(orderService.createOrder(any())).thenReturn(response);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(1));
    }
}
```
📌 Key Learning
- Use @WebMvcTest for controller slice testing
- Use @MockitoBean (NOT deprecated @MockBean)
- No DB involved here
  🪜 Step 8: Repository Test (@DataJpaTest)
```java
@DataJpaTest
class OrderRepositoryTest {

  @Autowired
  private OrderRepository repository;

  @Test
  void shouldFindByProductName() {

    OrderEntity entity = OrderEntity.builder()
            .productName("iPhone")
            .quantity(1)
            .price(BigDecimal.valueOf(100000))
            .status(OrderStatus.CREATED)
            .build();

    repository.save(entity);

    List<OrderEntity> result =
            repository.findByProductName("iPhone");

    assertThat(result).hasSize(1);
  }
} 
```

📌 Repository tests:
- Load JPA layer only
- No web, no service beans

✅ Phase 2 Summary
✔ Designed clean REST APIs<br/>
✔ Applied validation at the right layer<br/>
✔ Implemented global error handling<br/>
✔ Wrote proper controller & repository tests<br/>
✔ Avoided common Spring Boot anti-patterns<br/>

➡️ Next Phase:
---
## 🔹 PHASE 3 – Configuration & Profiles

### 🎯 Objective
Learn how **real-world Spring Boot applications manage configuration** across environments such as **dev, test, and prod**, and avoid common production issues.

This phase focuses on:
- Environment-specific configuration
- Spring Profiles
- Test configuration using Testcontainers
- Fixing real issues like **PostgreSQL timezone errors**
- Disabling unnecessary components during tests

---

## 🧠 Why This Phase Is Important

In real projects:
- You **never use the same config** for Dev, Test, and Prod
- Tests should **never touch production DB**
- Some features (Tracing, Metrics) must be **disabled in tests**
- Incorrect timezone configs can **break Hibernate startup**

This phase teaches **how professionals solve these problems**.

---

## 🪜 Step-by-Step Hands-On Guide

---

### ✅ Step 1: Understand Spring Profiles

Spring Profiles allow you to load **different configuration files** based on environment.

Common profiles:
- `dev` → Local development
- `test` → Automated tests
- `prod` → Production

Spring automatically loads:
- `application.properties`
- `application-{profile}.properties`

---

### ✅ Step 2: Create Profile-Based Config Files

Create the following files:

src/main/resources/<br/>
├── application.properties<br/>
├── application-dev.properties<br/>
├── application-test.properties<br/>
├── application-prod.properties<br/>

```yaml
### ✅ Step 3: Configure application-dev.properties

Used during **local development**.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/oms_dev
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
✅ Step 4: Configure application-test.properties

Used during automated tests.
```properties
spring.jpa.hibernate.ddl-auto=create-drop

# Fix PostgreSQL timezone issue
spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Kolkata

# Disable observability in tests
management.tracing.enabled=false
management.observations.enabled=false
```
📌 Why this matters

PostgreSQL does NOT accept Asia/Calcutta
- Hibernate fails during startup without correct timezone
- Tracing beans cause failures in sliced tests

✅ Step 5: Activate Profile in Tests

For integration and repository tests:
```java
@ActiveProfiles("test")
```
📌 This ensures:
- application-test.properties is loaded
- Test-only configuration is applied

✅ Step 6: Use Testcontainers for Database

Instead of using real PostgreSQL:
```java
@Container
static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("oms_test")
                .withUsername("test")
                .withPassword("test");
```
Inject container properties dynamically:
```java
@DynamicPropertySource
static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
}
```

✅ Result:
- Database starts only for tests
- No risk of touching prod data
- Clean DB for every test run

✅ Step 7: Disable Auto-Replacing Database

Spring tries to replace DB with H2 by default.

Disable it:
```java
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
```
📌 This allows Testcontainers PostgreSQL to be used.

✅ Step 8: Fix TimeZone at JVM Level (Optional)

Some environments still fail with timezone.

Safe fallback:
```java
@BeforeAll
static void setup() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
}
```
✅ Step 9: Disable Unwanted Beans in Tests

Why?
- Controllers & repositories do not need tracing
- Tracer bean causes test failures

Solution:

- Disable via properties
- OR make tracing optional using conditional beans

🧪 What You Learned in Phase 3

✔ Spring Profiles<br/>
✔ application-dev / test / prod separation<br/>
✔ Testcontainers with PostgreSQL<br/>
✔ Fixing Hibernate timezone issues<br/>
✔ Avoiding prod DB access in tests<br/>
✔ Disabling observability in test scope<br/>
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
### 🔹 Phase 4 – Day 1: Resilience4j (Retry & Circuit Breaker) with OMS

#### 🎯 Objective
Make the **Order Management System (OMS)** resilient to failures by implementing:
- **Retry**
- **Circuit Breaker**

You will learn **why**, **when**, and **how** to use Resilience4j in real-world Spring Boot applications.

---

#### 🧠 Why Resilience4j?

In distributed systems:
- Downstream services may be **slow**
- Services may **fail intermittently**
- Repeated failures can **cascade**

Resilience4j helps by:
- Retrying transient failures
- Preventing system overload using circuit breakers
- Recovering gracefully with fallbacks

---

#### 🛠 Step 1: Add Resilience4j Dependencies

Add the following to `pom.xml`:

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-retry</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-circuitbreaker</artifactId>
</dependency>
```
#### 🛠 Step 2: Identify Failure-Prone Logic

In OMS, typical failure points:

 - External payment service
 - Inventory service
 - Notification service

For learning purposes, we simulate failure inside OrderServiceImpl.
#### 🛠 Step 3: Enable Retry
Use Case<br/>
Retry order creation when a temporary failure occurs.<br/>
Update OrderServiceImpl
```java
@Retry(name = "orderRetry", fallbackMethod = "createOrderFallback")
public OrderResponseDto createOrder(OrderRequestDto request) {
    simulateFailure();
    return createOrderInternal(request);
}
```
Add Fallback Method
```java
public OrderResponseDto createOrderFallback(
        OrderRequestDto request, Exception ex) {

    throw new OrderCreationException(
        "Order creation failed after retries", ex);
}
```
#### 🛠 Step 4: Configure Retry in application.yml
```yaml
resilience4j:
  retry:
    instances:
      orderRetry:
        max-attempts: 3
        wait-duration: 2s
        retry-exceptions:
          - java.lang.RuntimeException
```
#### 🛠 Step 5: Enable Circuit Breaker
Why Circuit Breaker?

If retries keep failing:
 - Stop calling the failing logic
 - Fail fast
 - Protect system resources

Update Service Method
```java
@CircuitBreaker(name = "orderCircuitBreaker", fallbackMethod = "orderCircuitFallback")
public OrderResponseDto createOrder(OrderRequestDto request) {
    simulateFailure();
    return createOrderInternal(request);
}
```
Circuit Breaker Fallback
```java
public OrderResponseDto orderCircuitFallback(
        OrderRequestDto request, Throwable ex) {

        throw new ServiceUnavailableException(
        "Order service temporarily unavailable", ex);
        }
```
#### 🛠 Step 6: Configure Circuit Breaker
```yaml
resilience4j:
  circuitbreaker:
    instances:
      orderCircuitBreaker:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10s
        minimum-number-of-calls: 5
```
#### 🧪 Step 7: Test the Behavior
Expected Behavior<br/>

| Scenario            | Result                |
| ------------------- | --------------------- |
| Temporary failure   | Retried automatically |
| Continuous failures | Circuit opens         |
| Circuit open        | Immediate fallback    |
| After wait time     | Circuit half-open     |
