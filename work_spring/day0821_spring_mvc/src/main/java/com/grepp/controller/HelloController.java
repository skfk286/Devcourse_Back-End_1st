package com.grepp.controller;

import com.grepp.model.ParamDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("is here??");
        return "hi";
    }

//    @RequestMapping(value = "bye", method = RequestMethod.GET)
//    public ModelAndView bye(HttpServletRequest request) {
//        System.out.println("param1 : " + request.getParameter("param1"));
//        System.out.println("param2 : " + request.getParameter("param2"));
//        System.out.println("param2 num : " + (Integer.parseInt((request.getParameter("param2")) + 100)));
//        return null;
//    }

//    @GetMapping("/bye")
//    public ModelAndView bye(@RequestParam("param1") String p1,
//                            @RequestParam("param2") int p2) {
//        System.out.println("param1 : " + p1);
//        System.out.println("param2 : " + (p2 + 180) );
//
//        return null;
//    }

    @GetMapping("/bye")
    public ModelAndView bye(ParamDTO param) {
        System.out.println(param);

        return null;
    }

    @PostMapping(value = "/bye2", consumes = "application/json", produces = "application/json")
    public ModelAndView bye2(@RequestBody ParamDTO param) {
        System.out.println(param);

        return null;
    }
}
