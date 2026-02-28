# User Service

User service handles user profiles and follow relationship (social graph) between users


## Features
1. Creates new user for new registration (Authentication Service)
2. Provides user profile details
3. Accepts user edit requests for bio and full name
4. Allows user to follow/unfollow other users
5. Shares social graph with Post service for feed generation
6. Validates user details before any action
7. Authorizes user on every request on JWT (Zero-Trust Authorization)


## APIs
- /api/v1/profile/createUser/{email} - POST
- /api/v1/profile/viewProfile/{userId} - GET
- /api/v1/profile/editProfile/{userId} - PUT
- /api/v1/profile/follow/{userId} - POST
- /api/v1/profile/unfollow/{userId} - DELETE
- /api/v1/profile/deleteUser/{email} - DELETE
- /api/v1/profile/fetchFollowerId/{followeeId} - GET
- /api/v1/profile/fetchFolloweeId/{followerId} - GET


## 🚀 Running the Microservice

Follow the steps below to run this microservice locally.

### 1. Build the image with Docker
```bash
sudo docker build -t user-backend:1.0.1 .
```

### 2. Run the Docker image
```bash
sudo docker run -d -p 9016:9016 --name user-backend-container user-backend:1.0.1
```

### 🧩 Stopping the Container
```bash
docker stop user-backend-container
docker rm user-backend-container
```
