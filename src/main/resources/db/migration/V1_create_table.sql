CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    roll_no INT NOT NULL CHECK (char_length(roll_no::TEXT) <= 7),
    grade INT,
    email VARCHAR(255) CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);
