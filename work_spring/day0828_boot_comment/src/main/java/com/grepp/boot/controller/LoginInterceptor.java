package com.grepp.boot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("로그인 세션 체크 인터셉터 동작!");
        String loginId = (String) request.getSession().getAttribute("loginId");
        if(loginId == null) {
            System.out.println("로그인 세션이 없습니다..");
            response.sendRedirect(request.getContextPath() + "/member/loginForm.do");

            return false;
        }
        return true;
    }
}
