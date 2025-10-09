DROP DATABASE IF EXISTS homework;
CREATE DATABASE homework CHARACTER SET utf8;
USE homework;

DROP TABLE IF EXISTS user_table;
CREATE TABLE user_table(
id bigint primary key auto_increment,
username varchar(30) not null unique,
password varchar(30) not null,
nickname varchar(30),
phone varchar(30)
);
