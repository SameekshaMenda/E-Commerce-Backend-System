
# 🛒 E-Commerce Backend System

### 📘 Project Overview

This project is a **production-grade backend system** for an e-commerce platform built with **Spring Boot** and **MySQL**.
It manages users, products, carts, orders, and simulated payments using a **layered architecture** following industry best practices.

The backend exposes **REST APIs** that can be used by web or mobile clients for online shopping operations.

---

## 🚀 Features

### 👤 User Management
- User registration and login
- Profile update and password change
- Role-based access control (`ADMIN`, `CUSTOMER`)
- Admin can manage users

### 🛍 Product Management
- Admin can add, update, or delete products
- Products have details: `name`, `description`, `price`, `stock`, `category`, `imageUrl`, `rating`
- Customers can view products (with pagination & filtering)

### 🛒 Shopping Cart
- Each customer has a personal cart
- Add, update, or remove products
- View total price before checkout

### 📦 Order Management
- Convert cart to an order during checkout
- Store `orderId`, `date`, `items`, `total`, `status`
- Admin can update order status (`PLACED`, `SHIPPED`, `DELIVERED`, `CANCELLED`)
- Customers can view order history

### 💳 Payment Management (Simulated)
- Payment simulation for success/failure
- Auto-update order and payment status
- Deduct stock after successful purchase

---

## 🧱 Architecture


code
Markdown
# 🛒 E-Commerce Backend System

### 📘 Project Overview

This project is a **production-grade backend system** for an e-commerce platform built with **Spring Boot** and **MySQL**.
It manages users, products, carts, orders, and simulated payments using a **layered architecture** following industry best practices.

The backend exposes **REST APIs** that can be used by web or mobile clients for online shopping operations.

---

## 🚀 Features

### 👤 User Management
- User registration and login
- Profile update and password change
- Role-based access control (`ADMIN`, `CUSTOMER`)
- Admin can manage users

### 🛍 Product Management
- Admin can add, update, or delete products
- Products have details: `name`, `description`, `price`, `stock`, `category`, `imageUrl`, `rating`
- Customers can view products (with pagination & filtering)

### 🛒 Shopping Cart
- Each customer has a personal cart
- Add, update, or remove products
- View total price before checkout

### 📦 Order Management
- Convert cart to an order during checkout
- Store `orderId`, `date`, `items`, `total`, `status`
- Admin can update order status (`PLACED`, `SHIPPED`, `DELIVERED`, `CANCELLED`)
- Customers can view order history

### 💳 Payment Management (Simulated)
- Payment simulation for success/failure
- Auto-update order and payment status
- Deduct stock after successful purchase

---

## 🧱 Architecture
com.ecommerce
┣ 📂 controller → REST API endpoints
┣ 📂 service → Business logic
┣ 📂 repository → Spring Data JPA interfaces
┣ 📂 entity → JPA entities
┣ 📂 dto → Data Transfer Objects
┣ 📂 exception → Custom exception handling
┣ 📂 config → Security & configuration classes
┗ 📂 utils → Helper classes


---

## ⚙️ Tech Stack

| Component | Technology |
|------------|------------|
| Language | Java 17+ |
| Framework | Spring Boot 3+ |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| API | RESTful API |
| Build Tool | Maven |
| Security | Spring Security (Basic Auth / JWT optional) |
| Testing | JUnit 5, Mockito |
| Logging | SLF4J / Logback |

---

## 🖥 How to Run Locally

### 🔧 Prerequisites
- Java 17 or higher
- Maven 3.9+
- MySQL running locally
- Postman (for API testing)

### ⚙️ Steps

1️⃣ **Clone the repository:**
```bash
git clone https://github.com/<your-username>/ecommerce-backend.git
cd ecommerce-backend
```

2️⃣ Create the database:
```bash
CREATE DATABASE ecommercedb;
```

3️⃣ Configure database credentials in src/main/resources/application.properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ecommercedb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4️⃣ Build and run the project:
```bash
mvn clean install
mvn spring-boot:run
```

5️⃣ Access API endpoints:
```bash
Base URL: http://localhost:8080/api
```
