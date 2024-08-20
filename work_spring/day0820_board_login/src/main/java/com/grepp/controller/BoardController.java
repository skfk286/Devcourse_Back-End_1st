package com.grepp.controller;

import com.grepp.controller.util.MyPageInfo;
import com.grepp.model.dto.BoardDTO;
import com.grepp.model.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

public class BoardController implements MyController {
    private BoardService service = BoardService.getInstance();

    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        String url = req.getServletPath();
        if("/board/list.do".equals(url)) { // 목록보기
            List<BoardDTO> boardList = service.getBoard();
            req.setAttribute("bList", boardList);
            return new MyPageInfo(true, "/list");
        } else if("/board/read.do".equals(url)) { // 상세내용 보기
            int no = Integer.parseInt(req.getParameter("no"));
            req.setAttribute("bbb", service.read(no));
            return new MyPageInfo(true, "/view");
        } else if("/board/writeForm.do".equals(url)) { // 글 작성하러 가기
            return new MyPageInfo(true, "/write_form");
        } else if("/board/write.do".equals(url)) { // 글 작성 완료
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String writer = (String)req.getSession().getAttribute("loginId");
            service.write(new BoardDTO(title, content, writer));

            /** 아래와 같이 forward 하는 경우 기존 req가 직전 요청이 되고 새로고침 하면 같은게 또 write 되는 문제. */
            // return new MyPageInfo(true, "/main");
            return new MyPageInfo(false, "/main.do");
        }

        return null;
    }
}
