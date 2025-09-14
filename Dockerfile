# Use an official Java 17 runtime as base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy all files
COPY . .

# Build the application with Maven wrapper
RUN ./mvnw clean install -DskipTests

# Run the jar file (adjust the jar name if different)
CMD ["java", "-jar", "target/emtech-logistics-0.0.1-SNAPSHOT.jar"]
