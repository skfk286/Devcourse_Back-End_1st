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
        if("/board/write.do".equals(url)) {
            return new MyPageInfo(true, "/write");
        } else if("/board/list.do".equals(url)) {
            List<BoardDTO> boardList = service.getBoard();
            req.setAttribute("bList", boardList);
            return new MyPageInfo(true, "/list");
        } else if("/board/read.do".equals(url)) {
            int no = Integer.parseInt(req.getParameter("no"));
            req.setAttribute("bbb", service.read(no));
            return new MyPageInfo(true, "/view");
        }

        return null;
    }
}
