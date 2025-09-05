package com.egnotice0905.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.egnotice0905.demo.mapper")
public class MybatisConfig {
}
