CREATE DATABASE IF NOT EXISTS Event_db;

USE Event_db;

CREATE TABLE IF NOT EXISTS Event_table (
  id INTEGER IDENTITY PRIMARY KEY,
  name varchar(40),
  eventDate date,
  eventType varchar(30)
);