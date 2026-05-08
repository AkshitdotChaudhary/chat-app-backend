# 💬 Real-Time Chat Application — Backend

A production-grade, full-stack real-time chat application built with **Spring Boot**, **WebSocket (STOMP)**, **JWT Authentication**, and **MySQL**. Supports concurrent multi-user messaging with low-latency bidirectional communication.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Backend Framework | Spring Boot |
| Real-Time Communication | WebSocket + STOMP Protocol |
| Authentication | JWT (JSON Web Tokens) |
| Authorization | Role-Based Access Control (RBAC) |
| Database | MySQL |
| Frontend | Angular + RxJS |
| Build Tool | Maven |

---

## ✨ Features

- **Real-time messaging** — Bidirectional WebSocket communication via STOMP protocol for instant message delivery across all connected clients
- **JWT Authentication** — Secure stateless session management with token-based auth on every request
- **Role-Based Access Control** — Route-level authorization enforced throughout the application
- **Concurrent multi-user support** — Scalable session management handling multiple simultaneous connections with efficient state synchronization
- **Reactive frontend** — Angular UI using RxJS observables to consume live REST API and WebSocket events in real time
- **Robust error handling** — Structured exception handling and graceful recovery for connection failures

---

## 🏗️ Architecture

```
Client (Angular)
     │
     ├── REST API calls (HTTP + JWT)
     │        │
     │        ▼
     │   Spring Boot Controllers
     │        │
     │        ▼
     │   Service Layer (Business Logic)
     │        │
     │        ▼
     │   MySQL Database
     │
     └── WebSocket (STOMP)
              │
              ▼
         Message Broker
              │
              ▼
     Connected Clients (broadcast)
```

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0+

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/AkshitdotChaudhary/chat-app-backend.git
   cd chat-app-backend
   ```

2. **Configure the database** — Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/chatapp
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   jwt.secret=your_jwt_secret_key
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **The server starts at** `http://localhost:8080`

---

## 🔌 Key API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate and get JWT token |
| WS | `/ws` | WebSocket connection endpoint |
| STOMP | `/app/chat.send` | Send a message |
| STOMP | `/topic/messages` | Subscribe to receive messages |

---

## 🔒 Security

- All REST endpoints (except `/auth/**`) require a valid JWT token in the `Authorization: Bearer <token>` header
- WebSocket connections are authenticated on handshake
- RBAC enforces access at the route level

---

## 📁 Related Repository

- **Frontend (Angular):** [Link](https://github.com/AkshitdotChaudhary/chat-app-frontend)

---

## 👨‍💻 Author

**Akshit Chaudhary** — Backend Developer | Java • Spring Boot • Microservices

- 📧 akshitchaudhary640@gmail.com
- 💼 [LinkedIn](https://www.linkedin.com/in/akshit-chaudhary-b34839312)
- 🐙 [GitHub](https://github.com/AkshitdotChaudhary)
