package com.ycjung.jpa.controller;

import com.ycjung.jpa.model.dto.BoardDTO;
import com.ycjung.jpa.model.entity.BoardEntity;
import com.ycjung.jpa.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    /* 기존에 작성되던 형태. */
//    @RequestMapping("/write.do") // 글쓰기
//    public ModelAndView write(
//            BoardDTO board,
//            HttpSession session,
//            @RequestPart(value = "uploadFile", required = false) MultipartFile[] uploadFile) throws SQLException {
//
//    }

    @PostMapping
    public BoardDTO write(@RequestBody BoardDTO boardDTO) {
        BoardEntity entity = new BoardEntity();
        entity.setTitle(boardDTO.getTitle());
        entity.setWriter(boardDTO.getWriter());
        entity.setContent(boardDTO.getContent());

        boardRepository.save(entity);
        System.out.println(boardDTO);
        System.out.println(entity);
        return boardDTO;
    }

    @PostMapping
    public List<BoardDTO> list() {
        return boardRepository.findAl();
    }

}
