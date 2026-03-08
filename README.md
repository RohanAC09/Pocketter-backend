# Pocketter: A Social Media Platform

- A Platform for sharing insightful thoughts, similar to the Twitter (currently "X") platform.
- Users can post their thoughts on the platform. Also, they can see other posts as a feed feature "timeline"
- People can follow/unfollow other users
- User can view profiles, edit their own profile

## Table of Content
- [Features](#features)
- [Architecture Diagram](#architecture-diagram)
- [Trade-offs](#trade-offs)
- [Microservices](#microservices)
- [Tech Stack](#-tech-stack)
- [Deploying Containers](#deploying-containers)

## Features
- Microservice architecture
- Timelines fetched from Redis cache
- Fan-out-on-write while creating a post (non-celebrity accounts)
- Asynchronous processing of create-post & inject-to-timeline operation
- Centralised authentication with JWT
- Zero-trust authorisation at microservices

## Architecture Diagram
</p>
<p align="center">
<a><img width="900" height="700" src="https://github.com/user-attachments/assets/9146c6f7-72e9-4f10-ab96-6874e8267fe9" /> </a>
</p>

## Trade-offs
- **Consistency vs Availability**  
  - Availability 99.99% & moderate Consistency
  - System must respond to every request.
  - It is fine if we show feed with older posts. Example, feed was generated based on post ids stored in cache. That cache is stored for 2hr (TTL).
  - Even though the post ids fetched from Redis cache, contents are fetched every time, so updated contents will be visible.
  - If any user posted a new post, their post may not be visible to followers whose feed was generated from cache. (Depends on Kafka event queue size)
  - User follows another user, feed is generated from cache may not have newly followed person's posts (till cache TTL)
 
- **Fan-out Strategies**
  - Read-heavy architecture, where feed is fetched quickly from cache (Followers & Followees <1000/user)
  - Used fan-out-on-write strategy (Push model for Non-Celebrity users)
  - For new post, Kafka pushes it to all the followers' feed considering low number of followers
  - For Celebrity model ( >10,000 followers), it would be suitable to use pull model (Writes are cheaper & Reads are expensive)
  - For user following >5000 people, it is suggested to use "Hybrid" model as push posts while writing and pull limited post/user while reading
  - Advanced logic for higher following count - get top active users & fetch limited posts/user while reading.

## Microservices
1. Authentication Service - [Code & related files](https://github.com/RohanAC09/Pocketter-backend/tree/main/authentication-service)
2. User Profile Service - [Code & related files](https://github.com/RohanAC09/Pocketter-backend/tree/main/user-backend)
3. Post Service - [Code & related files](https://github.com/RohanAC09/Pocketter-backend/tree/main/post-backend)
4. Timeline Service - [Code & related files](https://github.com/RohanAC09/Pocketter-backend/tree/main/timeline)

## Deploying Containers

- Follow these steps to run all services using Docker.
  - Clone the repository  
    ```bash 
    git clone https://github.com/RohanAC09/Pocketter-backend.git
    cd Pocketter-backend/
    ```

  - Build & run the images with Docker-Compose  
    ```bash
    sudo docker compose up -d --build
    ```

  - List down Docker containers  
    ```bash
    sudo docker container ls -a
    ```

  - Stopping the containers
    ```bash
    sudo docker compose down
    ```


## 🧩 Tech Stack

1. Java
2. Docker (containerisation)
3. MySQL (DB)
4. Redis-cache
5. Kafka MQ
6. Spring-MVC
7. Spring-Security
8. JWT Authentication
