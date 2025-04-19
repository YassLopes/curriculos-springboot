#!/bin/sh

# Create necessary directories
mkdir -p /run/postgresql
chown postgres:postgres /run/postgresql

# Initialize PostgreSQL data directory if it's empty
if [ -z "$(ls -A /var/lib/postgresql/data)" ]; then
    echo "Initializing PostgreSQL data directory..."
    su postgres -c "initdb -D /var/lib/postgresql/data --auth-host=md5 --auth-local=trust"
fi

# Configure PostgreSQL to listen on all interfaces
echo "host all all 0.0.0.0/0 md5" >> /var/lib/postgresql/data/pg_hba.conf
echo "listen_addresses = '*'" >> /var/lib/postgresql/data/postgresql.conf

# Start PostgreSQL
echo "Starting PostgreSQL..."
su postgres -c "postgres -D /var/lib/postgresql/data -h 0.0.0.0" &
PG_PID=$!

# Wait for PostgreSQL to be ready
echo "Waiting for PostgreSQL to start..."
until pg_isready -h 0.0.0.0 -p 5432 -U postgres; do
    sleep 1
done

# Create database and user if they don't exist
echo "Creating database and user..."
if ! su postgres -c "psql -h 0.0.0.0 -p 5432 -U postgres -c '\du' | grep -q postgres"; then
    su postgres -c "psql -h 0.0.0.0 -p 5432 -U postgres -c \"CREATE USER postgres WITH SUPERUSER PASSWORD 'postgres';\""
fi

if ! su postgres -c "psql -h 0.0.0.0 -p 5432 -U postgres -l" | grep -q curriculum_db; then
    su postgres -c "psql -h 0.0.0.0 -p 5432 -U postgres -c \"CREATE DATABASE curriculum_db;\""
fi

# Start the Spring Boot application
echo "Starting Spring Boot application..."
java -jar app.jar &
APP_PID=$!

# Wait for both processes
wait $PG_PID $APP_PID 