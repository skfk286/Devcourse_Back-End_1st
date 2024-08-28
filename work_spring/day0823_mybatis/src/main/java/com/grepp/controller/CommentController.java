package com.grepp.controller;

import com.grepp.model.dto.CommentDTO;
import com.grepp.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/write.do")
    @ResponseBody // 아래 문장에서 @ResponseBody 가 없다면 viewResolver 가 동작할텐데, 이 어노테이션을 붙이면 데이터를 넘김.
    public String write(CommentDTO commentDTO) {
        System.out.println("/comment/write.do 호출 >");
        boardService.writeComment(commentDTO);
        return "comment write success";
    }

//    @GetMapping("/list.do")
//    public List<CommentDTO> list(@RequestParam("bno") int bno) { }

    @GetMapping("/list.do/{bbb}")
    @ResponseBody
    public List<CommentDTO> list(@PathVariable("bbb") int bno) throws SQLException {
        System.out.println("/comment/list.do 호출 >");

        return boardService.getComments(bno);
    }
}
