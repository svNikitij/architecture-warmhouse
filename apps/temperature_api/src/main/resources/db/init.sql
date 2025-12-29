CREATE TABLE IF NOT EXISTS sensors
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    type          VARCHAR(100) NOT NULL,
    location      VARCHAR(255) NOT NULL,
    unit          VARCHAR(50)  NOT NULL,
    current_value DOUBLE PRECISION,
    status        VARCHAR(50)  NOT NULL DEFAULT 'active',
    created_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO sensors (name, type, location, unit, status)
VALUES ('Living Room Temperature', 'temperature', 'Living Room', '°C', 'active'),
       ('Bedroom Temperature', 'temperature', 'Bedroom', '°C', 'active'),
       ('Kitchen Temperature', 'temperature', 'Kitchen', '°C', 'active');