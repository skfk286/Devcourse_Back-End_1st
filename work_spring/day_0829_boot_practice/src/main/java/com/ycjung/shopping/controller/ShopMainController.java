package com.ycjung.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopMainController {

    @RequestMapping("/main")
    public String main() {
        System.out.println("/main");
        return "main";
    }
}
