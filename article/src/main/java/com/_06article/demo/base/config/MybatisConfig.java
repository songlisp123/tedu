package com._06article.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com._06article.demo.mapper")
public class MybatisConfig {
}
