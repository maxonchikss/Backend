USE appDB;

DROP TABLE IF EXISTS records;
DROP TABLE IF EXISTS artists;

CREATE TABLE artists (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         country VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE records (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         year INT NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         artist_id INT,
                         FOREIGN KEY (artist_id) REFERENCES artists(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO artists (name, country) VALUES
                                        ('Captain Hollywood Project', 'Germany'),
                                        ('Haddaway', 'Germany'),
                                        ('Aqua', 'Denmark'),
                                        ('Snap!', 'Germany'),
                                        ('Sandra', 'Germany'),
                                        ('Masterboy', 'Germany'),
                                        ('Magic Affair', 'Germany');

INSERT INTO records (title, year, price, artist_id) VALUES
                                                        ('More and More', 1993, 25.99, 1),
                                                        ('All I Want', 1993, 24.99, 1),
                                                        ('What Is Love', 1993, 29.99, 2),
                                                        ('Life', 1993, 22.99, 2),
                                                        ('Aquarium', 1997, 34.99, 3),
                                                        ('Rhythm Is a Dancer', 1992, 27.99, 4),
                                                        ('The Power', 1990, 26.99, 4),
                                                        ('Maria Magdalena', 1985, 31.99, 5),
                                                        ('Feel the Heat of the Night', 1994, 23.99, 6),
                                                        ('Omen III', 1994, 28.99, 7);