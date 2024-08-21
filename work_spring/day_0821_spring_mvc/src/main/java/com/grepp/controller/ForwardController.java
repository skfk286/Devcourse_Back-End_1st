package com.grepp.controller;

import com.grepp.model.ParamDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/forward")
public class ForwardController {

    @RequestMapping("/mav")
    public ModelAndView mav() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("view1");
        mav.addObject("msg", "Forward 컨트롤러가 뷰에게 전달하는 데이터");
        mav.addObject("now", new Date());

        return mav;
    }

    // return 되는 String을 뷰 이름으로 하고 전달받은 파라미터 DTO 객체와 추가로 데이터를 더 담은 model 의 gugudan 들도
    // 모두 뷰에게 전달할 수 있음.
    @RequestMapping("/str")
    public String str(@ModelAttribute("param") ParamDTO param, Model model) {
        model.addAttribute("gugudan", 6);
        return "view2";
    }

    @PostMapping("/redirect")
    public String redirect(@RequestParam("age") int age, RedirectAttributes attr) {
        attr.addAttribute("age", age); // hahaha 한테 재 요청 보낼때 이 데이터도 보낼 수 있음.
        return "redirect:hahaha"; // hahaha.jsp 를 찾는게 아니라 /forward/hahaha 라는 요청을 다시 보내게 된다.
    }

    @GetMapping("/hahaha")
    public String hahaha(@RequestParam(value = "age", defaultValue = "1") int age) {
        System.out.println("hahaha receive ?? " + age);
        return "view3";
    }
}
