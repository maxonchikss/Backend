USE appDB;

DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE movies (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        user_id INT,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO users (first_name, last_name) VALUES
                                              ('Quentin', 'Tarantino'),
                                              ('Nicolas', 'Winding Refn'),
                                              ('James', 'Cameron');

INSERT INTO movies (title, user_id) VALUES
                                        ('My Best Friend''s Birthday', 1),
                                        ('Pulp Fiction', 1),
                                        ('Django Unchained', 1),
                                        ('Pusher', 2),
                                        ('Pusher II: With Blood on My Hands', 2),
                                        ('Pusher III: I''m the Angel of Death', 2),
                                        ('Titanic', 3),
                                        ('Avatar', 3),
                                        ('The Terminator', 3);