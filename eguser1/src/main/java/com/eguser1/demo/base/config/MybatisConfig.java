package com.eguser1.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.eguser1.demo.mapper")
public class MybatisConfig {
}
