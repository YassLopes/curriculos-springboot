# Use multi-stage build
FROM postgres:15-alpine as postgres
ENV POSTGRES_DB=curriculum_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
COPY init.sql /docker-entrypoint-initdb.d/
EXPOSE 5432

FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM alpine:3.19
WORKDIR /app

# Install PostgreSQL and its dependencies
RUN apk add --no-cache \
    postgresql15 \
    postgresql15-client \
    postgresql15-contrib \
    libpq \
    libxml2 \
    libxslt \
    openssl \
    icu \
    krb5 \
    openldap \
    zstd \
    lz4 \
    && mkdir -p /var/lib/postgresql/data \
    && chown -R postgres:postgres /var/lib/postgresql/data

# Install OpenJDK
RUN apk add --no-cache openjdk17-jre

# Copy PostgreSQL binaries and libraries
COPY --from=postgres /usr/local/bin/postgres /usr/local/bin/
COPY --from=postgres /usr/local/lib/postgresql /usr/local/lib/postgresql
COPY --from=postgres /usr/local/share/postgresql /usr/local/share/postgresql

# Copy the Spring Boot application
COPY --from=build /app/target/*.jar app.jar

# Create a script to start both services
COPY start.sh /app/
RUN chmod +x /app/start.sh

# Set environment variables
ENV PGDATA=/var/lib/postgresql/data
ENV POSTGRES_DB=curriculum_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

EXPOSE 5432 8080
CMD ["/app/start.sh"] 