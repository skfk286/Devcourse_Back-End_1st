package com.ycjung.day0904.config;

import com.ycjung.day0904.util.YcjungFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity // dispatcherServlet 보다도 더 먼저 진 행되어야 하는 설정이라 tomcat 이 읽고 반영
public class MySecurityConfig {

    @Autowired
    public YcjungFilter ycjungFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // cors 일단 비활성화
        return http.cors(AbstractHttpConfigurer::disable) // 추 후에 프론트와 연결할 때 프론트 서버 CORS 만 풀고 나머지는 불허해야 한다.
                .csrf(AbstractHttpConfigurer::disable) // cookie 등을 활용하지 않고 있어서 csrf 비활성화, 추 후에 csrf 공격을 막고싶으면 여기 활성화 하고 쿠키들의 설정을 해줘야한다.
                .httpBasic(AbstractHttpConfigurer::disable) // Basic 인증 환경이 아니고 jwt 토큰이니까 Basic 모드 비활성화
                .sessionManagement(AbstractHttpConfigurer::disable) // sessionless 기반 아님으로 비활성화
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/auth/**").permitAll(); // 로그인 하러 오는 url 은 허용
                    req.anyRequest().authenticated(); // 나머지는 다 인증되야 들어오게 할거다.
                })
                // .addFilter(ycjungFilter)
                .addFilterAfter(ycjungFilter, CorsFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// 시큐리티 6 이전 버전에서는 아래처럼
//public class MySecurityConfig extends WebSecurityConfigurerAdapter{
//    @Override
//    protected void configure(HttpSecurity http){
//        http~~~~ 설정
//        http.cors() // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정.
//        .and()
//        .csrf()// csrf는 현재 사용하지 않으므로 disable
//        .disable()
//        .httpBasic()// token을 사용하므로 basic 인증 disable
//        .disable()
//        .sessionManagement()  // session 기반이 아님을 선언
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests() // /와 /auth/** 경로는 인증 안해도 됨.
//        .antMatchers("/", "/auth/**").permitAll()
//        .anyRequest() // /와 /auth/**이외의 모든 경로는 인증 해야됨.
//        .authenticated();
//    }
//}