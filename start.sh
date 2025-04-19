#!/bin/sh

# Start PostgreSQL in the background
postgres -D /var/lib/postgresql/data &
PG_PID=$!

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 10

# Start the Spring Boot application
java -jar app.jar &
APP_PID=$!

# Wait for both processes
wait $PG_PID $APP_PID 