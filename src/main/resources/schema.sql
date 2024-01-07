CREATE DATABASE IF NOT EXISTS alfa;
USE alfa;

CREATE TABLE IF NOT EXISTS encrypted_files (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               file_name VARCHAR(255) NOT NULL,
                                               encrypted_content LONGBLOB NOT NULL
);
