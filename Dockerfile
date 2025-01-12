# Use a Maven image to build the project
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /build

# Copy the whole project to the container
COPY . .

# Build the project, including all modules, and package it
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image for running the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory for the application
WORKDIR /app

# Copy the packaged application JAR from the build stage
COPY --from=build /build/map-service/target/map-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (adjust if needed)
EXPOSE 18081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]