package com.grepp.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YcjungController {
    @RequestMapping("/ycjung")
    public String ycjung() {
        return "hello.html";
    }

    @RequestMapping("/programmers")
    public String programmers() {
        return "programmers.html";
    }
}
