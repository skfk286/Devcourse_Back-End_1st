package com.grepp.controller;


import com.grepp.model.dto.BoardDTO;
import com.grepp.model.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/list.do") // 리스트
    public ModelAndView list(@RequestParam(name = "page", defaultValue = "1") int page) throws SQLException {
        System.out.println("/list.do 호출 >");

        ModelAndView mav = new ModelAndView("list");
        mav.addObject("pageData", boardService.getBoards(page));

        return mav;
    }

    // writeForm.do
    @RequestMapping("/writeForm.do") // 글쓰기 화면
    public String writeForm() {
        System.out.println("/writeForm.do 호출 >");

        return "write_form";
    }
    
    @RequestMapping("/write.do") // 글쓰기
    public ModelAndView write(BoardDTO board, HttpServletRequest request) throws SQLException {
        System.out.println("/write.do 호출 >");
        ModelAndView mav = new ModelAndView("alert");

        board.setWriter((String) request.getSession().getAttribute("loginId"));

        int result = boardService.write(board);
        if(result >= 1) {
            mav.addObject("msg", "Write Success!");
            mav.addObject("path", "list.do");
        } else {
            mav.addObject("msg", "Write Failed!");
            mav.addObject("path", "write.do");
        }

        return mav;
    }

    @RequestMapping("/read.do")
    public ModelAndView read(@RequestParam(name = "no") int no) throws SQLException {
        System.out.println("/read.do 호출 >");
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("bbb", boardService.read(no));

        return mav;
    }
}
