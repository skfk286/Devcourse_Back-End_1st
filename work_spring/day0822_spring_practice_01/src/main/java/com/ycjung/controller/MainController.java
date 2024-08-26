package com.ycjung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public String index() {
        System.out.println("/main.do 호출 >");

        return "main";
    }
}
