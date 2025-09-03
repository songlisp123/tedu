DROP DATABASE IF EXISTS cloud_vehicle_db;
CREATE DATABASE cloud_vehicle_db CHARACTER SET utf8;
USE cloud_vehicle_db;

-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id          bigint AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    username    varchar(20) COMMENT '用户名',
    password    varchar(20) COMMENT '密码',
    email       varchar(50) COMMENT '邮箱',
    phone       varchar(20) COMMENT '手机号',
    create_time datetime COMMENT '创建时间',
    update_time datetime COMMENT '更新时间'
) COMMENT '用户表' auto_increment = 101;

-- 车辆表
DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle
(
    id           bigint auto_increment comment '车辆id' primary key,
    brand        varchar(30) comment '车辆品牌',
    license      varchar(20) comment '车牌号',
    model        varchar(100) comment '车辆型号',
    code         varchar(100) comment '车辆识别码',
    displacement varchar(20) comment '车辆排量',
    type         varchar(50) comment '车辆类型',
    color        varchar(10) comment '车辆颜色',
    battery_type varchar(50) comment '电池类型',
    create_time  datetime comment '创建时间',
    update_time  datetime comment '更新时间',
    user_id      BIGINT comment '用户id'
) comment '车辆表' auto_increment = 201;

-- 公告表
DROP TABLE IF EXISTS notice;
CREATE TABLE notice
(
    id          bigint(20) PRIMARY KEY AUTO_INCREMENT,
    title       varchar(128)  NOT NULL COMMENT '通知标题',
    content     varchar(2000) NOT NULL COMMENT '通知的的内容',
    type        tinyint(1)    NOT NULL DEFAULT 0 COMMENT '状态：0公告，1通知',
    user_id     bigint(20)    NOT NULL COMMENT '用户id',
    create_time datetime      COMMENT '发布时间',
    update_time datetime      COMMENT '更新时间',
    status      tinyint(1)    NOT NULL DEFAULT 0 COMMENT '状态：0正常，1关闭'
) comment '公告表' auto_increment = 301;