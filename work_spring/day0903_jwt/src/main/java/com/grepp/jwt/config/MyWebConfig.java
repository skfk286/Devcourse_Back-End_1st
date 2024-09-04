package com.grepp.jwt.config;

import com.grepp.jwt.interceptor.MyLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    private MyLoginInterceptor myLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myLoginInterceptor)
                .order(1) // 인터셉터가 여러 개인 경우 처리 순서
                .addPathPatterns("/todo", "/board");
    }
}
