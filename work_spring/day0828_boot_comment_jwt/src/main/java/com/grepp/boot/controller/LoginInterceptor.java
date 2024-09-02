package com.grepp.boot.controller;

import com.grepp.boot.jwt.InvalidJwtTokenException;
import com.grepp.boot.jwt.Jwt;
import com.grepp.boot.model.service.JwtTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("JWT 토큰 인증 인터셉터 동작!");

        // 쿠키에서 accessToken 추출
        Cookie accessCookie = null;
        String accessToken = null;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    accessCookie = cookie;
                    accessToken = cookie.getValue();
                } else if("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        if (refreshToken == null) {
            System.out.println("RefreshToken 쿠키가 존재하지 않습니다.");
            response.sendRedirect(request.getContextPath() + "/member/loginForm.do");
            if(accessCookie != null) {
                System.out.println("AccessToken 쿠키가 남아있어 삭제합니다.");
                accessCookie.setValue("");
                accessCookie.setPath("/");
                accessCookie.setMaxAge(0);
                response.addCookie(accessCookie);
            }
            return false;
        }

        if (accessToken == null) {
            System.out.println("AccessToken 쿠키가 존재하지 않습니다.");
            response.sendRedirect(request.getContextPath() + "/member/loginForm.do");
            return false;
        }

        try {
            // JWT 토큰 검증
            Jwt jwt = jwtTokenService.getAccessTokenInfo(accessToken);
            System.out.println("유효한 토큰입니다. 사용자: " + jwt.getLoginId());

            // 추가적으로 필요한 작업이 있다면 여기서 수행

            return true; // 인증 성공

        } catch (InvalidJwtTokenException e) {
            System.out.println("유효하지 않은 토큰입니다.");
            response.sendRedirect(request.getContextPath() + "/member/loginForm.do");
            return false;
        }
    }
}
