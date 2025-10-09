DROP DATABASE IF EXISTS homework;
CREATE DATABASE homework CHARACTER SET utf8;
USE homework;

#用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id          bigint AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    username    varchar(20) COMMENT '用户名',
    password    varchar(20) COMMENT '密码',
    email       varchar(50) COMMENT '邮箱',
    phone       varchar(20) COMMENT '手机号',
    age         int COMMENT '年龄'
) COMMENT '用户表' auto_increment = 101;