CREATE DATABASE hotel;
use hotel;

CREATE TABLE `user` (
	userName VARCHAR(50) PRIMARY KEY NOT NULL,
    userPassword VARCHAR(50) NOT NULL
);

INSERT INTO `user` (userName,userPassword) VALUES ('adminhotel','khongmatkhau');
