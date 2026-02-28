# Timeline Service

Timeline service shows the latest posts by followed people


## Features
1. Fetch the timeline
2. Get post ids from Redis cache
3. Call Post service for post contents
4. If cache absent, call Post service and get generated timeline
5. Authorize each request & validate JWT

## APIs
- /api/v1/timeline - POST

## 🚀 Running the Microservice

Follow the steps below to run this microservice locally.

### 1. Build the image with Docker
```bash
sudo docker build -t timline-backend:1.0.1 .
```

### 2. Run the Docker image
```bash
sudo docker run -d -p 8087:8087 --name timeline-backend-container timeline-backend:1.0.1
```

### 🧩 Stopping the Container
```bash
docker stop timeline-backend-container
docker rm timeline-backend-container
```
