CREATE DATABASE IF NOT EXISTS Event_db

USE Event_db;

CREATE TABLE IF NOT EXISTS Event_table (
  id INTEGER IDENTITY PRIMARY KEY,
  name varchar(40),
  event_date date,
  event_type varchar(30)
);