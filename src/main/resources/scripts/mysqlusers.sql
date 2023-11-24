DROP DATABASE IF EXISTS pantrypal_db;

DROP USER IF EXISTS `pantrypaladmin`@`%`;
DROP USER IF EXISTS `pantrypaluser`@`%`;

CREATE DATABASE IF NOT EXISTS pantrypal_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `pantrypaladmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `pantrypal_db`.* TO `pantrypaladmin`@`%`;

CREATE USER IF NOT EXISTS `pantrypaluser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `pantrypal_db`.* TO `pantrypaluser`@`%`;

FLUSH PRIVILEGES;