package com.notice.demo.base.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.notice.demo.mapper")
public class MybatisConfig {
}
