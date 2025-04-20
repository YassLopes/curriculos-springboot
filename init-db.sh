#!/bin/bash
set -e

echo "Waiting for PostgreSQL to start..."
until pg_isready -U postgres -d curriculum_db; do
  sleep 1
done

echo "PostgreSQL is ready!" 