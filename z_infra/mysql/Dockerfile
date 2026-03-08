FROM mysql:latest

# Set default environment variables
ENV MYSQL_ROOT_PASSWORD=root@3105
ENV MYSQL_USER=dockeruser
ENV MYSQL_PASSWORD=Rohan@3105

# Copy initialization SQL script
COPY init.sql /docker-entrypoint-initdb.d/