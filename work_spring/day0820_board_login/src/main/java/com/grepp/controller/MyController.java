package com.grepp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MyController {
    // 규칙, 약속
    Object handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
