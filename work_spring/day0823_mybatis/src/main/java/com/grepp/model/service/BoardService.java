package com.grepp.model.service;

import com.grepp.model.dto.BoardDTO;
import com.grepp.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private static final int COUNT_PER_PAGE = 10;

    @Autowired
    private BoardRepository repo;

    // 글 읽기를 수행할 때 작성자와 읽는 사용자가 일치하는지 검사해서 조회수 증가 update 여부를 판단하거나,
    // 이미 이 글을 읽는 사용자는 조회수가 중복해서 증가하지 않도록 검사하거나
//    public BoardDTO read(int bno,String loginId) throws SQLException {
//        BoardDTO board = repo.selectOne(bno);
//        if (!board.getWriter().equals(loginId)) {
//            repo.updateReadCount(bno); // 조회수 증가 시키기
//        }
//
//        return board;
//    }

    // 위에처럼 로직을 수행하는 메소드. 지금은 처리하지 않았음.
    public BoardDTO read(int bno) throws SQLException {
        return repo.selectOne(bno);
    }

    public int write(BoardDTO board) throws SQLException {
        return repo.insert(board);
    }

    public List<BoardDTO> getBoard() throws SQLException {
        return repo.selectAll();
    }

//    public int update(BoardDTO board) throws SQLException {
//        return repo.update(board);
//    }

    // 현재 보고자 하는 페이지 정보가 들어왔을 때, 실제 해당 페이지에 보여져야 하는 List<BoardDTO>를 포함해서
    // 페이지가 총 몇개 필요하고, 하단 페이지 링크 1~10 or 11~20 같은 페이지 구간 계산
    public Map<String, Object> getBoards(int page) throws SQLException {
        int totalCount = repo.selectCount(); // 총 개시글의 갯수 (ex : 27)
        int totalPageCount = totalCount / COUNT_PER_PAGE; // ex : 27/10 = 2.7 = 2page 일단 필요하고

        if(totalCount % COUNT_PER_PAGE > 0) { // 10개씩 2페이지 하고 7개의 글이 남은 상태라 페이지 하나 늘려주기.
            totalPageCount++;
        }

        int startPage = (page - 1) / 10 * 10 + 1; // 현재 페이지가 11, 12, 13, ..., 20 이었을 때 10~19로 바꾸고, /10*10 하면 11, 12, ..., 19으로 동일함
        int endPage = startPage + 9;
        if(totalPageCount < endPage) { // 21 ~ 30 이라고 계산했는데 총 페이지수가 28밖에 없었다 ?
            endPage = totalPageCount; // 마지막 페이지 링크를 총 페이지 수로 줄여준다.
        }

        int startRow = (page - 1) * COUNT_PER_PAGE; // 한 페이지당 보여질 글의 갯수를 반영해서 DB에 모든 글을 다 읽어오지 않고, 여기서 부터 끊어서 읽으라고 알려준다.
        List<BoardDTO> boardList = repo.selectList(startRow, COUNT_PER_PAGE);

        /////////////////////// Service는 이렇게 여러가지 비즈니스 로직을 수행하여 데이터를 계사한다.
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("page", page);
        resultData.put("startPage", startPage);
        resultData.put("endPage", endPage);
        resultData.put("totalPageCount", totalPageCount);
        resultData.put("bList", boardList);

        return resultData;
    }
}
