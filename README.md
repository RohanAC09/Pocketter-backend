# Pockeeter: A Social Media Platform

- A Platform for sharing insightful thoughts, similar to the Twitter (currently "X") platform.
- Users can post their thoughts on the platform. Also, they can see other posts as a feed feature "timeline"
- People can follow/unfollow other users
- User can view profiles, edit their own profile

## Features
- Microservice architecture
- Timelines fetched from Redis cache
- Fan-out-on-write while creating a post (non-celebrity accounts)
- Asynchronous processing of create-post & timeline operation
- Centralised authentication with JWT
- Zero-trust authorisation at microservices

## Microservices
1. Authentication Service [Code](https://github.com/RohanAC09/Pocketter-backend/tree/main/authentication-service)
2. User Profile Service [Code](https://github.com/RohanAC09/Pocketter-backend/tree/main/user-backend)
3. Post Service [Code](https://github.com/RohanAC09/Pocketter-backend/tree/main/post-backend)
4. Timeline Service [Code](https://github.com/RohanAC09/Pocketter-backend/tree/main/timeline)

---

### 🧩 Tech Stack

1. Java
2. Docker
3. MySQL
4. Redis
5. Spring-Security
