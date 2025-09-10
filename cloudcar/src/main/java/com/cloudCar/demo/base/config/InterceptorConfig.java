package com.cloudCar.demo.base.config;

import com.cloudCar.demo.base.Interceptors.RateLimitInterceptor;
import com.cloudCar.demo.base.Interceptors.TimeAcessInterceptor;
import com.cloudCar.demo.base.Interceptors.UserLoginCheckInterceptor;
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
        registry.addInterceptor(new RateLimitInterceptor())
                        .addPathPatterns("/v1/user/**");
        registry.addInterceptor(new UserLoginCheckInterceptor())
                        .addPathPatterns("/v1/car/add",
                                "/v1/car/list",
                                "/v1/car/del/{id}");
        registry.addInterceptor(new carInterceptor())
                .addPathPatterns("/v1/car/**");
        registry.addInterceptor(new carInterceptor())
                .addPathPatterns("/v1/user/**")
                .excludePathPatterns("/v1/user/login");

    }
}
