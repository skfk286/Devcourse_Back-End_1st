package com.ycjung.day0904.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity // dispatcherServlet 보다도 더 먼저 진행되어야 하는 설정이라 tomcat 이 읽고 반영
public class MySecurityConfig {
    
}

// 시큐리티 6 이전 버전에서는 아래처럼
class MySecurityConfig2 extends WebSecurityConfigurationAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 설정을 이곳에서 진행.
        http.cors()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .and
    }
}