package com.grepp.controller;

import com.grepp.controller.util.MyPageInfo;
import com.grepp.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public class MemberController implements MyController {
    private MemberService memberService = MemberService.getInstance();

    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        String url = req.getServletPath();

        Object result = null;
        if("/member/loginForm.do".equals(url)) {
            result = new MyPageInfo(true, "/login_form");
        } else if("/member/login.do".equals(url)) {
            String userid = req.getParameter("userid");
            String userpw = req.getParameter("userpw");
            String loginid = memberService.login(userid, userpw);

            if(loginid != null) {
                HttpSession session = req.getSession();
                session.setAttribute("loginId", loginid);
                req.setAttribute("msg", "login success!");
                req.setAttribute("path", req.getContextPath() + "/main.do");
                result = new MyPageInfo(true, "/alert");
            } else {
                req.setAttribute("msg", "login failed!");
                req.setAttribute("path", req.getContextPath() + "/loginForm.do");
                result = new MyPageInfo(true, "/alert");
            }
        } else if("/member/logout.do".equals(url)) {
            req.getSession().invalidate(); // 현재 요청보낸 클라이언트의 세션 객체 해제
            req.setAttribute("msg", "logout success!");
            req.setAttribute("path", req.getContextPath() + "/main.do");
            result = new MyPageInfo(true, "/alert");
        }

        return result;
    }
}
