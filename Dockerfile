# Use a multi-stage build to reduce the final image size
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the POM file first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests -Dmaven.javadoc.skip=true -Dmaven.source.skip=true

# Create the final image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/*.jar app.jar

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE=production

# Expose the port
EXPOSE 8080

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 