package com.grepp.boot.controller;

import com.grepp.boot.model.dto.CommentDTO;
import com.grepp.boot.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @GetMapping("/list.do/{bbb}") // 아래와 같이 ResponseEntity 를 설정할 수 있다.
//    @ResponseBody
//    public ResponseEntity<?> list(@PathVariable("bbb") int bno) throws SQLException {
//        System.out.println("/comment/list.do 호출 >");
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(new MediaType("application", "text", Charset.forName("UTF-8")));
//        //return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); // 클라이언트에게 하고 싶은 서버의 응답 정보.
//        return ResponseEntity.ok().headers(header).body(boardService.getComments(bno).toString());
//    }
}
