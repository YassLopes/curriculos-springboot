#!/bin/sh

# Initialize PostgreSQL data directory if it's empty
if [ -z "$(ls -A /var/lib/postgresql/data)" ]; then
    echo "Initializing PostgreSQL data directory..."
    su postgres -c "initdb -D /var/lib/postgresql/data"
fi

# Start PostgreSQL
echo "Starting PostgreSQL..."
su postgres -c "postgres -D /var/lib/postgresql/data" &
PG_PID=$!

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 10

# Create database and user if they don't exist
echo "Creating database and user..."
su postgres -c "psql -c \"CREATE USER postgres WITH SUPERUSER PASSWORD 'postgres';\" || true"
su postgres -c "psql -c \"CREATE DATABASE curriculum_db;\" || true"

# Start the Spring Boot application
echo "Starting Spring Boot application..."
java -jar app.jar &
APP_PID=$!

# Wait for both processes
wait $PG_PID $APP_PID 