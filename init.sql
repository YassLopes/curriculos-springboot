-- Create the database if it doesn't exist
CREATE DATABASE curriculum_db;

-- Connect to the database
\c curriculum_db;

-- Create the schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS curriculum;

-- Set the search path
SET search_path TO curriculum; 