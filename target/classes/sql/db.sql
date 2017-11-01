CREATE TABLE IF NOT EXISTS Event_table (
  id INTEGER NOT NULL PRIMARY KEY,
  name varchar(40),
  event_date date,
  event_type varchar(30)
);