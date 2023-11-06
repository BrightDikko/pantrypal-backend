DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    email      VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE users
    ADD CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
