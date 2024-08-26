package com.grepp.controller;

import com.grepp.model.dto.BoardDTO;
import com.grepp.model.dto.FileDTO;
import com.grepp.model.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public ModelAndView write(
            BoardDTO board,
            HttpServletRequest request,
            @RequestPart(value = "uploadFile", required = false) MultipartFile[] uploadFile) throws SQLException {
        System.out.println(uploadFile[0].getOriginalFilename());
        System.out.println(uploadFile[1].getOriginalFilename());

        System.out.println("/write.do 호출 >");
        ModelAndView mav = new ModelAndView("alert");

        board.setWriter((String) request.getSession().getAttribute("loginId"));
        int result = boardService.write(board); // 첨부파일 저장 이전에 일단 게시글 부터 작성완료 되어야 한다.

        System.out.println("글 작성 결과 : " + result);
        System.out.println("방금 작성한 글 : " + board);

        try {
            List<FileDTO> savedFiles = saveFiles(uploadFile);
            System.out.println("파일 저장 완료 : " + savedFiles);
            System.out.println("파일 정보 디비에 기록 완료 : " + boardService.saveFileInfos(savedFiles, board.getNo()));
        } catch (IOException ex) {
            System.out.println("파일 저장 실패");
            ex.printStackTrace();
        }

        if (result >= 1) {
            mav.addObject("msg", "Write Success!");
            mav.addObject("path", "list.do");
        } else {
            mav.addObject("msg", "Write Failed!");
            mav.addObject("path", "write.do");
        }

        return mav;
    }

    private List<FileDTO> saveFiles(MultipartFile[] uploadFile) throws IOException {
        List<FileDTO> fileDTOList = new ArrayList<>();
        if (uploadFile != null && uploadFile.length > 0) { // 첨부된 파일이 확실히 있는 경우 저장절차 진행
            String uploadPath = "C:/programmers_upload/";
            if (new File(uploadPath).exists() == false) {
                new File(uploadPath).mkdir();
            }

            for (MultipartFile file : uploadFile) {
                String savedName = new Random().nextInt(1000000000) + ""; // 확률상 10억범위 랜덤이면 안겹치겠지.
                File savedFile = new File(uploadPath + savedName);

                file.transferTo(savedFile); // 클라이언트가 업로드한 파일을 서버 컴퓨터 폴더에 비어있는 파일 경로로 저장시키는 메소드!
                FileDTO saveFileInfo = new FileDTO();
                saveFileInfo.setSavedPath(uploadPath + savedName);
                saveFileInfo.setOriginalName(file.getOriginalFilename());

                fileDTOList.add(saveFileInfo);
            }
        }

        return fileDTOList;
    }

    @RequestMapping("/read.do")
    public ModelAndView read(@RequestParam(name = "no") int no) throws SQLException {
        System.out.println("/read.do 호출 >");
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("bbb", boardService.read(no));

        return mav;
    }
}
