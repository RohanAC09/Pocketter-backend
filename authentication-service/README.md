# Authentication Service

Authentication service validates user credentials and provides Json-Web Token (JWT).


## Features
1. Register new users
2. Validate existing user credentials and provide new JWT
3. can validate existing JWT
4. Password Encoding with BCrypt
5. Hashing HS256 + salt
6. Authentication done by Authentication service, Authorisation performed for each request by every microservice (Zero-Trust Strategy)

## Class Flow
<img width="500" height="300" src="https://github.com/user-attachments/assets/c22f6b9b-06a7-42b1-a23c-fff77818125e" />

## APIs
- /api/v1/auth/register - POST
- /api/v1/auth/login - POST
- /api/v1/auth/validate/jwt - POST

1. Register User
<img width="700" height="350" alt="Screenshot from 2026-02-18 19-14-46" src="https://github.com/user-attachments/assets/650fe433-4a2a-442b-9ab9-252b6e1056ea" />

2. Login
<img width="700" height="350" alt="Screenshot from 2026-02-18 19-13-58" src="https://github.com/user-attachments/assets/83b918c1-99ba-4274-bc95-a446d1e54e04" />

3. Validate JWT
<img width="700" height="346" alt="Screenshot from 2026-02-18 19-45-04" src="https://github.com/user-attachments/assets/3dbee561-0f49-4c60-8d49-fd803ce8d6e6" />

4. Error Handling
<img width="700" height="350" alt="Screenshot from 2026-02-18 19-44-14" src="https://github.com/user-attachments/assets/f7fa5d33-3b0e-480e-b6e2-86ca680d9cb1" />

<!-- <p align="center">
<a href="https://github.com/RohanAC09/Expense-Insights-Frontend" target="blank"><img width="707" height="300" alt="Screenshot from 2025-09-20 14-31-14" src="https://github.com/user-attachments/assets/7d87b785-fd66-4d36-a22d-5d67fbf733c7" /></a>
</p> -->

## 🚀 Running the Microservice

Follow the steps below to run this microservice locally.

### 1. Build the image with Docker
```bash
sudo docker build -t authentication-service:1.0.1 .
```

### 2. Run the Docker image
```bash
sudo docker run -d -p 9016:9016 --name authentication-service-container authentication-service:1.0.1
```

### 🧩 Stopping the Container
```bash
docker stop authentication-service-container
docker rm authentication-service-container
```
