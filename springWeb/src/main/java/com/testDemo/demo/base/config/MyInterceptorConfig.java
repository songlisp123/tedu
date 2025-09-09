package com.testDemo.demo.base.config;

import com.testDemo.demo.base.inteceptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyInterceptor())
//                .addPathPatterns("/v1/**");
        //只拦截一个
//        registry.addInterceptor(new MyInterceptor())
//                .addPathPatterns("/v1/user/login");
        //拦截多个
//        registry.addInterceptor(new MyInterceptor())
//                .addPathPatterns("/v1/user/login","/v1/user/add");

        //排除某方法的拦截
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/v1/user/**")
                .excludePathPatterns("/v1/user/changePassword");
    }
}
