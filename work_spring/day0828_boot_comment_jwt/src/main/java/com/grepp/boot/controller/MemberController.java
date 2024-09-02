package com.grepp.boot.controller;

import com.grepp.boot.jwt.Jwt;
import com.grepp.boot.model.dto.MemberDTO;
import com.grepp.boot.model.service.JwtTokenService;
import com.grepp.boot.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/loginForm.do")
    public String login() {
        return "login_form";
    }

//    @PostMapping("/login.do")
//    public ModelAndView login(MemberDTO member, HttpSession session, HttpServletRequest request) throws SQLException {
//        ModelAndView mav = new ModelAndView("alert");
//
//        MemberDTO memberDTO = memberService.login(member.getUserid(), member.getUserpw());
//        System.out.println("정보 : " + memberDTO.toString());
//        String loginId = memberDTO.getUsernickname();
//        if(loginId != null) {
//            session.setAttribute("loginId", loginId);
//            mav.addObject("msg", "Login Success!");
//            mav.addObject("path", request.getContextPath() + "/main.do");
//        } else {
//            mav.addObject("msg", "Login Failed!");
//            mav.addObject("path", request.getContextPath() + "/member/loginForm.do");
//        }
//
//        return mav;
//    }

    @PostMapping("/login.do")
    public ModelAndView login(MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ModelAndView mav = new ModelAndView("alert");

        MemberDTO memberDTO = memberService.login(member.getUserid(), member.getUserpw());
        System.out.println("정보 : " + memberDTO.toString());

        if (memberDTO != null) {  // 로그인 성공 시
            // JWT 토큰 생성
            Jwt jwt = jwtTokenService.createTokens(memberDTO.getUserid(), memberDTO.getUsernickname(), ""); // socialAccessToken은 필요없으면 빈 문자열 전달

            // HTTP-Only 쿠키에 Access Token 추가
            Cookie accessTokenCookie = new Cookie("accessToken", jwt.getAccessToken());
            accessTokenCookie.setHttpOnly(true);  // JavaScript에서 접근 불가하도록 설정
            accessTokenCookie.setSecure(false);    // HTTP와 HTTPS에서 모두 전송 가능하도록 설정
            accessTokenCookie.setPath("/");       // 애플리케이션의 모든 경로에서 유효하도록 설정
            accessTokenCookie.setMaxAge((int) (jwt.getAccessTokenExp().getTime() - System.currentTimeMillis()) / JwtTokenService.SESSION_TIMEOUT_UNIT); // JWT 만료 시간에 맞게 쿠키의 만료 시간 설정

            // HTTP-Only 쿠키에 Refresh Token 추가 (선택 사항)
            Cookie refreshTokenCookie = new Cookie("refreshToken", jwt.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(false);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge((int) (jwt.getRefreshTokenExp().getTime() - System.currentTimeMillis()) / JwtTokenService.SESSION_TIMEOUT_UNIT);

            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);

            mav.addObject("msg", "Login Success!");
            mav.addObject("path", request.getContextPath() + "/main.do");
        } else {  // 로그인 실패 시
            mav.addObject("msg", "Login Failed!");
            mav.addObject("path", request.getContextPath() + "/member/loginForm.do");
        }

        return mav;
    }

    @GetMapping("/logout.do")
    public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("alert");

        // 1. 세션 무효화 (Invalidate the session)
        if (session != null) {
            session.invalidate();
        }

        // 2. 쿠키 제거 (Remove cookies)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName()) || "refreshToken".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); // 쿠키 즉시 만료
                    response.addCookie(cookie);
                }
            }
        }

        // 3. 로그아웃 후 리다이렉트 설정
        mav.addObject("msg", "Logout Success!");
        mav.addObject("path", request.getContextPath() + "/main.do");

        return mav;
    }

    @PostMapping("/extendAccessToken")
    public void extendAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        /* TODO : 아래코드에서 리프레쉬 토큰이 유효 하면 accessToken 을 재 설정해야 될 것 같음. */
    }
}
