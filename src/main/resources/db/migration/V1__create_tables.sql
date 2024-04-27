CREATE TABLE IF NOT EXISTS security_user (
     id INT AUTO_INCREMENT PRIMARY KEY,
     firstname VARCHAR(255) NOT NULL,
     lastname VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS token (
     id INT AUTO_INCREMENT PRIMARY KEY,
     token VARCHAR(255) NOT NULL UNIQUE,
     token_type VARCHAR(50) NOT NULL,
     revoked BOOLEAN NOT NULL,
     expired BOOLEAN NOT NULL,
     user_id INT,
     FOREIGN KEY (user_id) REFERENCES security_user(id)
);