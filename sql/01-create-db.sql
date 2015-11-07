CREATE DATABASE alchemytest CHARACTER SET UTF8;
CREATE USER alchemyuser@localhost IDENTIFIED BY 'Password';
GRANT ALL PRIVILEGES ON alchemytest.* TO alchemyuser@localhost;
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'alchemyuser'@'localhost';