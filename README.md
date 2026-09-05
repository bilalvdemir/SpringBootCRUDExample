# User CRUD API - Spring Boot 3.x

Modern, production-ready Spring Boot 3.x REST API with MongoDB, Redis caching, monitoring, and observability.

## 🚀 Features

### Core Functionality
- ✅ Full CRUD operations for User management
- ✅ RESTful API design with proper HTTP status codes
- ✅ DTO pattern for request/response separation
- ✅ MapStruct for efficient DTO mapping

### Architecture & Design
- ✅ Clean layered architecture (Controller → Service → Repository)
- ✅ Spring Boot 3.2.1 with Java 17
- ✅ MongoDB for data persistence
- ✅ Redis for caching
- ✅ Lombok for code reduction

### Data Validation & Exception Handling
- ✅ Jakarta Validation (JSR-380)
- ✅ Custom validation annotations
- ✅ Global exception handling with @RestControllerAdvice
- ✅ Standardized error responses with error codes
- ✅ Runtime exceptions (no checked exceptions)

### Performance & Resilience
- ✅ Redis caching with Spring Cache
- ✅ Pagination support
- ✅ Resilience4j (Circuit Breaker, Retry, Rate Limiter)
- ✅ Optimistic locking with @Version

### Monitoring & Observability
- ✅ Spring Boot Actuator
- ✅ Prometheus metrics export
- ✅ Grafana dashboards
- ✅ Custom business metrics
- ✅ Health checks
- ✅ Distributed tracing support
- ✅ Structured JSON logging (Logstash format)

### API Documentation
- ✅ OpenAPI 3.0 (Swagger)
- ✅ Interactive API documentation UI

### Testing
- ✅ Unit tests with JUnit 5 & Mockito
- ✅ Repository tests with Testcontainers
- ✅ AssertJ for fluent assertions

### DevOps
- ✅ Docker & Docker Compose
- ✅ Multi-stage Dockerfile
- ✅ Health checks in containers
- ✅ Non-root container user

---

## 📋 Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose
- MongoDB 7.0+ (or use Docker)
- Redis 7+ (or use Docker)

---

## 🛠️ Quick Start

### 1. Using Docker Compose (Recommended)

```bash
# Start all services (MongoDB, Redis, App, Prometheus, Grafana)
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down
```

### 2. Local Development

```bash
# Start MongoDB and Redis
docker-compose up -d mongodb redis

# Run the application (using Maven wrapper)
./mvnw spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/springbootcrudexample-1.0.0.jar

# Note: You can also use 'mvn' instead of './mvnw' if Maven is installed globally
```

---

## 📡 API Endpoints

Base URL: `http://localhost:8090/api/v1`

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users (paginated) |
| GET | `/users/{username}` | Get user by username |
| POST | `/users` | Create new user |
| PUT | `/users/{id}` | Update user |
| DELETE | `/users/{username}` | Delete user |

### Example Requests

**Create User:**
```bash
curl -X POST http://localhost:8090/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "SecurePass123!",
    "name": "John",
    "lastname": "Doe"
  }'
```

**Get All Users (Paginated):**
```bash
curl "http://localhost:8090/api/v1/users?page=0&size=20&sort=createdAt,desc"
```

**Update User:**
```bash
curl -X PUT http://localhost:8090/api/v1/users/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newemail@example.com",
    "name": "John Updated"
  }'
```

---

## 📊 Monitoring & Observability

### Swagger UI
- **URL:** http://localhost:8090/swagger-ui.html
- Interactive API documentation and testing

### Actuator Endpoints
- **Health:** http://localhost:8090/actuator/health
- **Info:** http://localhost:8090/actuator/info
- **Metrics:** http://localhost:8090/actuator/metrics
- **Prometheus:** http://localhost:8090/actuator/prometheus

### Prometheus
- **URL:** http://localhost:9090
- Metrics scraping and storage

### Grafana
- **URL:** http://localhost:3000
- **Username:** admin
- **Password:** admin
- Pre-configured Prometheus datasource

---

## 🏗️ Project Structure

```
src/main/java/com/bilald/crudexample/
├── config/                 # Configuration classes
│   ├── CacheConfig.java
│   ├── MongoConfig.java
│   ├── OpenApiConfig.java
│   └── ResilienceConfig.java
├── controller/             # REST Controllers
│   └── UserController.java
├── dto/                    # Data Transfer Objects
│   ├── request/
│   │   ├── CreateUserRequest.java
│   │   └── UpdateUserRequest.java
│   └── response/
│       ├── ErrorResponse.java
│       └── UserResponse.java
├── exception/              # Exception handling
│   ├── BusinessException.java
│   ├── ErrorCode.java
│   ├── GlobalExceptionHandler.java
│   ├── UserAlreadyExistsException.java
│   └── UserNotFoundException.java
├── health/                 # Custom health indicators
│   └── DatabaseHealthIndicator.java
├── mapper/                 # MapStruct mappers
│   └── UserMapper.java
├── model/                  # Domain entities
│   └── User.java
├── monitoring/             # Custom metrics
│   └── UserMetrics.java
├── repository/             # Data access layer
│   └── UserRepository.java
└── service/                # Business logic
    ├── UserService.java
    └── UserServiceImpl.java
```

---

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report

# Run specific test
./mvnw test -Dtest=UserServiceImplTest
```

---

## 🔧 Configuration

Key configuration in `application.yml`:

```yaml
# MongoDB
spring.data.mongodb.host=${MONGODB_HOST:localhost}
spring.data.mongodb.port=${MONGODB_PORT:27017}
spring.data.mongodb.database=${MONGODB_DATABASE:usercrud}

# Redis
spring.data.redis.host=${REDIS_HOST:localhost}
spring.data.redis.port=${REDIS_PORT:6379}

# Server
server.port=${SERVER_PORT:8090}

# Actuator
management.endpoints.web.exposure.include=health,info,metrics,prometheus
```

---

## 📈 Performance Features

### Caching Strategy
- **User lookups** cached for 30 minutes
- **Pagination results** cached for 60 minutes
- **Cache eviction** on create/update/delete operations

### Resilience Patterns
- **Circuit Breaker:** 50% failure threshold, 5s wait time
- **Retry:** 3 attempts with exponential backoff
- **Rate Limiter:** 100 requests per second

---

## 🔒 Validation Rules

### Username
- Must start with a letter
- Can contain letters, numbers, and special chars (., $, ;)

### Password
- Minimum 8 characters
- Must contain: uppercase, lowercase, digit, special character

### Email
- Valid email format required

---

## 📝 Error Handling

All errors follow a standardized format:

```json
{
  "errorCode": "4001",
  "message": "User not found: johndoe",
  "timestamp": "2024-01-15T10:30:00Z",
  "path": "/api/v1/users/johndoe",
  "traceId": "abc123def456",
  "fieldErrors": {
    "email": "Email must be valid"
  }
}
```

### Error Codes
- **4001:** User not found
- **4002:** User already exists
- **4100:** Validation error
- **5000:** Internal server error

---

## 🚦 Health Checks

The application includes comprehensive health checks:

- **MongoDB:** Database connectivity
- **Redis:** Cache availability
- **Disk Space:** Available storage
- **Custom:** Business-specific health indicators

---

## 📦 Building for Production

```bash
# Build optimized JAR
./mvnw clean package

# Build Docker image
docker build -t user-crud-api:1.0.0 .

# Run production container
docker run -d \
  -p 8090:8090 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e MONGODB_HOST=your-mongo-host \
  -e REDIS_HOST=your-redis-host \
  user-crud-api:1.0.0
```

---

## 📚 Technology Stack

- **Framework:** Spring Boot 3.2.1
- **Language:** Java 21 LTS
- **Database:** MongoDB 7.0
- **Cache:** Redis 7
- **Build Tool:** Maven 3.9
- **Validation:** Jakarta Validation
- **Mapping:** MapStruct 1.5.5
- **Logging:** Logback with Logstash encoder
- **Monitoring:** Actuator, Prometheus, Grafana
- **Resilience:** Resilience4j
- **API Docs:** SpringDoc OpenAPI 3
- **Testing:** JUnit 5, Mockito, Testcontainers
- **Utilities:** Lombok

---

## 🎯 Best Practices Implemented

✅ Clean Code principles
✅ SOLID principles
✅ Effective Java patterns
✅ 12-Factor App methodology
✅ RESTful API design
✅ Domain-Driven Design (DDD) concepts
✅ Separation of Concerns
✅ Dependency Injection
✅ Fail-fast approach
✅ Defensive programming

---

## 📄 License

MIT License

---

## 🤝 Contributing

Please read [CHANGELOG.md](https://github.com/bilalvdemir/SpringBootCRUDExample/blob/master/CHANGELOG.md) for details.

---

## 👤 Authors

* **Bilal Demir** - *Initial work* - [bilalvdemir](https://github.com/bilalvdemir)
