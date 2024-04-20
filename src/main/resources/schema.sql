CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    employee_id VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    designation VARCHAR(100) NOT NULL,
    salary INT NOT NULL,
    experience FLOAT NOT NULL
);