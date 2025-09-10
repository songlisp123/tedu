DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户编号',
    username    VARCHAR(20) COMMENT '用户名',
    password    CHAR(80) COMMENT '密码',
    nickname    VARCHAR(20) COMMENT '昵称',
    create_time TIMESTAMP COMMENT '注册时间'
) CHARSET = UTF8;
-- 文章表
DROP TABLE IF EXISTS article;
CREATE TABLE article
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '文章编号',
    title   VARCHAR(20) COMMENT '文章标题',
    content TEXT COMMENT '文章内容',
    user_id BIGINT COMMENT '用户编号'
) CHARSET = UTF8;

INSERT INTO user(username, password, nickname, create_time)
VALUES ('admin', '123456', 'admin', '2000-01-01 00:00:00');