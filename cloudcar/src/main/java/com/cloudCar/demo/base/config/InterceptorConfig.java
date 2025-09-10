package com.cloudCar.demo.base.config;

import com.cloudCar.demo.base.Interceptors.TimeAcessInterceptor;
import com.cloudCar.demo.base.Interceptors.carInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeAcessInterceptor())
                .addPathPatterns("/v1/**");
        registry.addInterceptor(new carInterceptor())
                .addPathPatterns("/v1/car/**");
        registry.addInterceptor(new carInterceptor())
                .addPathPatterns("/v1/user/**")
                .excludePathPatterns("/v1/user/login");

    }
}
