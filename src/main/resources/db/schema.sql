DROP SCHEMA IF EXISTS im_system;
CREATE SCHEMA IF NOT EXISTS im_system DEFAULT CHARACTER SET utf8;
USE im_system;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NUll UNIQUE,
    password VARCHAR(32) NOT NULL,
    gender VARCHAR(32) NOT NULL,
    signature VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS contacts;
CREATE TABLE IF NOT EXISTS contacts (
    id INT NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS user_contact;
CREATE TABLE IF NOT EXISTS user_contact (
    user_id INT NOT NULL,
    contact_id INT NOT NULL,
    name VARCHAR(32) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, contact_id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS conversations;
CREATE TABLE IF NOT EXISTS conversations (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(32) NOT NULL,
    creator_id INT NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    delete_time TIMESTAMP,
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS participants;
CREATE TABLE IF NOT EXISTS participants (
    id INT NOT NULL AUTO_INCREMENT,
    conversation_id INT NOT NULL,
    user_id INT NOT NULL,
    type VARCHAR(32) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS messages;
CREATE TABLE IF NOT EXISTS messages (
    id INT NOT NULL AUTO_INCREMENT,
    conversation_id INT NOT NULL,
    sender_id INT NOT NULL,
    message_type VARCHAR(32) NOT NULL,
    message_context VARCHAR(255) NOT NULL DEFAULT '',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    delete_time TIMESTAMP,
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;

DROP TABLE IF EXISTS attachments;
CREATE TABLE IF NOT EXISTS attachments (
    id INT NOT NULL AUTO_INCREMENT,
    message_id INT NOT NULL,
    file_address VARCHAR(255) NOT NULL,
    file_check_code VARCHAR(32),
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
)ENGINE=InnoDB;
SHOW WARNINGS;



