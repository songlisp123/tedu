CREATE DATABASE IF NOT EXISTS mvcdb DEFAULT CHARSET = UTF8;
USE mvcdb;
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(50) COMMENT '用户名',
    password    VARCHAR(50) COMMENT '密码',
    nickname    VARCHAR(50) COMMENT '昵称',
    create_time TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP COMMENT '更新时间'
);