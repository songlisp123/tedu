package com.egadress0904.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.egadress0904.demo.mapper")
public class MybatisConfig {
}
