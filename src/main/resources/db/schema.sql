DROP SCHEMA IF EXISTS im_system;
CREATE SCHEMA IF NOT EXISTS im_system DEFAULT CHARACTER SET utf8;
USE im_system;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NUll UNIQUE,
    password VARCHAR(32) NOT NULL,
    gender ENUM('male', 'female') NOT NULL,
    email VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS contacts;
CREATE TABLE IF NOT EXISTS contacts (
    id INT(11) NOT NULL,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS user_contact;
CREATE TABLE IF NOT EXISTS user_contact (
    user_id INT(11) NOT NULL,
    contact_id INT(11) NOT NULL,
    name VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, contact_id),
    FOREIGN KEY (user_id)
        REFERENCES users(id),
    FOREIGN KEY (contact_id)
        REFERENCES contacts(id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS conversations;
CREATE TABLE IF NOT EXISTS conversations (
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(32) NOT NULL,
    creator_id INT(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (creator_id)
        REFERENCES users(id),
)ENGINE=InnoDB;

DROP TABLE IF EXISTS participants;
CREATE TABLE IF NOT EXISTS participants (
    id INT(11) NOT NULL AUTO_INCREMENT,
    conversation_id INT(11) NOT NULL,
    user_id INT(11) NOT NULL,
    type ENUM('single', 'group') NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (conversation_id)
        REFERENCES conversations(id),
    FOREIGN KEY (user_id)
        REFERENCES users(id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS messages;
CREATE TABLE IF NOT EXISTS messages (
    id INT(11) NOT NULL AUTO_INCREMENT,
    conversation_id INT(11) NOT NULL,
    sender_id INT(11) NOT NULL,
    messages_type ENUM('text', 'file', 'photo') NOT NULL,
    message VARCHAR(255) NOT NULL DEFAULT '',
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (conversation_id)
        REFERENCES conversations(id),
    FOREIGN KEY (sender_id)
        REFERENCES users(id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS attachments;
CREATE TABLE IF NOT EXISTS attachments (
    id INT(11) NOT NULL AUTO_INCREMENT,
    message_id INT(11) NOT NULL,
    file_url VARCHAR(40) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (message_id)
        REFERENCES message(id)
)ENGINE=InnoDB;



