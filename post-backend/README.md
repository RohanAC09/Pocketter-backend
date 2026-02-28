# Post Service

Post service allows to create posts and see latest posts by followed people


## Features
1. Create new post
2. Authorize each request & validate JWT
3. Connects with User service and Timeline service for generating feed
4. Triggers new Kafka event after each new post
5. Fetch posts by ids received from cache
6. Collect all the posts for user whom they are following

## APIs
- /api/v1/post/createPost - POST
- /api/v1/post/getPostsByIds - POST
- /api/v1/post/getPostsForUser/{userId} - GET


## 🚀 Running the Microservice

Follow the steps below to run this microservice locally.

### 1. Build the image with Docker
```bash
sudo docker build -t post-backend:1.0.1 .
```

### 2. Run the Docker image
```bash
sudo docker run -d -p 8086:8086 --name post-backend-container post-backend:1.0.1
```

### 🧩 Stopping the Container
```bash
docker stop post-backend-container
docker rm post-backend-container
```
