
# 💳 E-Wallet Payment System

A secure and user-friendly E-Wallet Payment System built with **Spring Boot** and **React**, featuring **JWT authentication**, **MySQL** database integration, **Flyway** for database migrations, and a clean **Material UI** frontend.

## 🚀 Features

### Backend (Spring Boot)
- User Registration and Login with JWT Authentication
- Wallet Management (Create, View Balance)
- Transaction Management (Send/Receive Money)
- Role-Based Access Control (User/Admin)
- RESTful APIs
- Flyway-based DB migration
- Layered architecture (Controller, Service, Repository)
- Global Exception Handling
- CORS and Spring Security Configurations

### Frontend (React + Material UI)
- Modern UI with Material UI components
- User Authentication (Login/Register)
- View Wallet Balance
- Send and Receive Money
- Dashboard with user-specific info
- JWT-based secure routing
- Responsive Design

## 🛠️ Tech Stack

| Layer         | Technology                |
| ------------- | -------------------------- |
| Frontend      | React, Axios, Material UI |
| Backend       | Spring Boot, Spring Security, JWT |
| Database      | MySQL                     |
| Migration     | Flyway                    |
| Authentication| JWT (JSON Web Tokens)     |
| API Testing   | Postman                   |
| Build Tools   | Maven, npm                |

## 📁 Project Structure

```
E-Wallet-Payment-System/
├── ewallet-backend/         # Spring Boot Backend
│   ├── src/
│   │   └── main/java/com/ewallet
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── service/
│   │       └── config/
├── ewallet-frontend/        # React Frontend
│   ├── public/
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── services/
│       └── App.js
```

## ⚙️ Getting Started

### 🔧 Prerequisites
- Java 17+
- Node.js + npm
- MySQL
- Maven

## 🧩 Backend Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/thillainirmal-tech/E-Wallet-Payment-System.git
   cd E-Wallet-Payment-System/ewallet-backend
   ```

2. Configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ewallet
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=validate
   spring.flyway.enabled=true
   jwt.secret=your_jwt_secret_key
   ```

3. Run the app:
   ```bash
   mvn spring-boot:run
   ```

4. Backend will start at: `http://localhost:8080`

## 🌐 Frontend Setup

1. Go to frontend directory:
   ```bash
   cd ../ewallet-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. Frontend will run on: `http://localhost:3000`

## 🔐 API Endpoints (Sample)

| Endpoint                | Method | Description           |
|------------------------|--------|-----------------------|
| `/api/auth/register`   | POST   | User Registration     |
| `/api/auth/login`      | POST   | User Login            |
| `/api/wallet/balance`  | GET    | Get Wallet Balance    |
| `/api/transaction/send`| POST   | Send Money            |
| `/api/transaction/receive`| POST | Receive Money         |

## 📸 Screenshots

> (Add UI screenshots here)

## 🧪 Testing

- Use Postman or frontend UI to test authentication and wallet operations.
- Ensure JWT is passed in headers for protected routes.

## 📝 License

This project is licensed under the [MIT License](LICENSE).

## 👨‍💻 Author

**K. Thillai Nirmal**  
🔗 [GitHub](https://github.com/thillainirmal-tech)  
📧 shanmugakannan7549@gmail.com  

## 🌟 Show Your Support

If you liked this project, give it a ⭐️ on GitHub and share it with others!
 
