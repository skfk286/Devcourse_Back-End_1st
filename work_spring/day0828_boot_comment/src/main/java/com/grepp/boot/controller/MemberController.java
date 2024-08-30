package com.grepp.boot.controller;

import com.grepp.boot.model.dto.MemberDTO;
import com.grepp.boot.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/loginForm.do")
    public String login() {
        return "login_form";
    }

    @PostMapping("/login.do")
    public ModelAndView login(MemberDTO member, HttpSession session, HttpServletRequest request) throws SQLException {
        ModelAndView mav = new ModelAndView("alert");

        String loginId = memberService.login(member.getUserid(), member.getUserpw());
        if(loginId != null) {
            session.setAttribute("loginId", loginId);
            mav.addObject("msg", "Login Success!");
            mav.addObject("path", request.getContextPath() + "/main.do");
        } else {
            mav.addObject("msg", "Login Failed!");
            mav.addObject("path", request.getContextPath() + "/member/loginForm.do");
        }

        return mav;
    }

    @GetMapping("/logout.do")
    public ModelAndView logout(HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("alert");

        session.invalidate();
        mav.addObject("msg", "Logout Success!");
        mav.addObject("path", request.getContextPath() + "/main.do");

        return mav;
    }
}
