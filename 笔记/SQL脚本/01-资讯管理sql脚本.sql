CREATE DATABASE IF NOT EXISTS jyblog CHARACTER SET UTF8;
USE jyblog;
DROP TABLE IF EXISTS notice;
CREATE TABLE notice
(
    id          bigint(20) PRIMARY KEY AUTO_INCREMENT,
    title       varchar(128)  NOT NULL COMMENT '通知标题',
    content     varchar(2000) NOT NULL COMMENT '通知的的内容',
    type        tinyint(1)    NOT NULL DEFAULT 0 COMMENT '状态：0公告，1通知',
    user_id     bigint(20)    NOT NULL COMMENT '用户id',
    create_time datetime      NOT NULL COMMENT '发布时间',
    update_time datetime      NOT NULL COMMENT '更新时间',
    status      tinyint(1)    NOT NULL DEFAULT 0 COMMENT '状态：0正常，1关闭'
);