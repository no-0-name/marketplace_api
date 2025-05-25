# Marketplace API

![Java](https://img.shields.io/badge/java-21-red)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.2.1-brightgreen)
![PostgreSQL](https://img.shields.io/badge/postgresql-42.7.5-blue)
![Redis](https://img.shields.io/badge/redis-6.2.4-orange)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

REST API for an online marketplace with authentication, product management, shopping cart, and order processing.

## 🌟 Features
- 🔐 JWT Authentication
- 🛒 Shopping Cart Management
- 📦 Order Processing System
- 🛠️ CRUD Operations for Products & Categories
- 👮 Role-Based Access Control (USER/ADMIN)
- 📅 Order Timestamp Logging
- ⚡ Redis Caching

## 🛠 Technologies
- **Java**
- **PostgresQL**
- **Lombok**
- **Spring Boot**
- **JWT**
- **Redis**
- **Maven**: Maven

## 🚀 Getting Started
### Prerequisites
- Java 21+
- PostgreSQL 15+
- Redis 6.2+
- Maven 3.9+

### Configuration
1. Create a PostgreSQL database
2. For install redis on your pc use `sudo apt install redis-cli`
3. Configure `application.properties`:
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username_db
spring.datasource.password=your_password_db

# JWT
jwt.secret=your_jwt_secrete
jwt.expiration=86400000

# Redis
spring.redis.host=localhost
spring.redis.port=6379
spring.cache.type=redis
spring.cache.redis.time-to-live=3600000
```

## 👥 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
