USE appDB;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO users (first_name, last_name) VALUES
                                              ('David', 'Chese'),
                                              ('Tony', 'Siriko'),
                                              ('Steven', 'Van Zandt'),
                                              ('James ', 'Gandolfini'),
                                              ('Lorraine', 'Bracco');