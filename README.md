


# 🦖 Zilla Order – Microservices Architecture

Zilla Order is a microservices-based order management system designed for scalability, flexibility, and experimentation with modern tools like Kubernetes, Kafka, and CI/CD pipelines.

## 🧱 Architecture Overview

This project currently consists of the following microservices:

| Service          | Description                            | Database     |
|------------------|----------------------------------------|--------------|
| `Inventory`      | Manages product stock availability     | PostgreSQL   |
| `Order`          | Handles order creation and validation  | PostgreSQL   |
| `Product`        | Manages product catalog                | MongoDB      |

All services expose RESTful APIs documented via **Swagger**.

![d1](https://github.com/user-attachments/assets/b06b1baf-5357-44c2-8686-3eb9d8cecf93)

“In my Zilla Order project, which simulated a microservices-based e-commerce system, I used Kafka and RabbitMQ together, each playing a specific role.

RabbitMQ was responsible for queuing transactional tasks between services — like placing an order or updating inventory — where I needed guaranteed delivery with fast, isolated processing.

On the other hand, Kafka was used as an event backbone to log and monitor distributed events, like OrderCreated, ProductReserved, or PaymentConfirmed, making it easier to trace the entire lifecycle of a request across the system.

I implemented retry logic using Spring Cloud Retry, and for autoscaling, I containerized everything with Docker, orchestrated by Kubernetes, allowing the consumers to scale based on workload.”

## 🚀 Getting Started (Local)

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven

### Running the Project

```bash
# Clone the repository
git clone https://github.com/your-user/zilla-order.git
cd zilla-order

# Start all services using Docker Compose
docker-compose up --build
````

Each service will be available at:


* `http://localhost:8081` → **Inventory Service**
* `http://localhost:8082` → **Order Service**
* `http://localhost:8083` → **Product Service**

---

## 📘 API Documentation (Swagger)

Each service includes its own Swagger documentation:

| Service   | Swagger URL                             |
| --------- | --------------------------------------- |
| Inventory | `http://localhost:8081/swagger-ui.html` |
| Order     | `http://localhost:8082/swagger-ui.html` |
| Product   | `http://localhost:8083/swagger-ui.html` |

---

## 📦 Technologies Used

* Java 21 + Spring Boot
* PostgreSQL & MongoDB
* Docker & Docker Compose
* Swagger (OpenAPI)
* Maven

---

## 🧭 Roadmap

✅ MVP: Inventory, Order, Product services
🔜 API Gateway, Circuit Breaker, Resilience
🔜 Kafka & RabbitMQ Integration
🔜 Auth & Notification Microservices
🔜 Elasticsearch, Kibana, Prometheus
🔜 CI/CD with GitHub Actions & Kubernetes

---

## 🤝 Contributing

If you'd like to contribute or discuss improvements, feel free to open an issue or pull request!

---

## 📄 License

This project is licensed under the MIT License.

```

---
```
