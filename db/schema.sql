CREATE TABLE IF NOT EXISTS hall
(
    id   SERIAL PRIMARY KEY,
    name varchar(40) UNIQUE
);
CREATE TABLE IF NOT EXISTS seat
(
    id       SERIAL PRIMARY KEY,
    row      INT,
    "column" INT,
    hall_id  INT REFERENCES hall (id),
    status   BOOLEAN
);
CREATE TABLE IF NOT EXISTS account
(
    id      SERIAL PRIMARY KEY,
    name    varchar(50),
    phone   INT,
    seat_id INT REFERENCES seat (id)
);
INSERT INTO hall(name)
values ('ONE-3D');

INSERT INTO seat(row, "column", hall_id, status)
values (1, 1, 1, false), (1, 2, 1, false), (1, 3, 1, false),
       (2, 1, 1, false), (2, 2, 1, false), (2, 3, 1, false),
       (3, 1, 1, false), (3, 2, 1, false), (3, 3, 1, false);