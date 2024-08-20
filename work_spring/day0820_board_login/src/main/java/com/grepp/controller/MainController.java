package com.grepp.controller;

import com.grepp.controller.util.MyPageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController implements MyController {

    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse res) {
        return new MyPageInfo(true, "/main"); // /WEB-INF/views/main.jsp
    }
}
