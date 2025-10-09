package com._01web.demo.base.webconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com._01web.demo.*.mapper")
public class mabatisConfig {
}
