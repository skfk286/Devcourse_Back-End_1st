package com.grepp.controller;

import com.grepp.controller.util.MyControllerMapping;
import com.grepp.controller.util.MyPageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// board.do, member.do, login.do, ... etc 모두 다 이거로 실행되게
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class MainServlet extends HttpServlet {

    private MyControllerMapping controllerMapping = new MyControllerMapping();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        System.out.println("request here : " + url);

        // 로그인 세션 처리
        HttpSession session = request.getSession();
        String loginId = (String) session.getAttribute("loginId");
        if(url.startsWith("/board") && !url.endsWith("/list.do") && loginId == null) { // 게시판작업 하고 싶은데 && 목록은 아니고 && 로그인 정보 없다면.
            request.setAttribute("msg", "로그인 정보가 필요합니다.");
            request.setAttribute("path", request.getContextPath() + "/member/loginForm.do");
            request.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(request, response);
            return;
        }

        try {
            MyController controller = controllerMapping.getController(url); // 애한테 물어보면 컨트롤러 객체중 하나 준다.

            Object controllerResult = null;
            if (controller != null) { // 해당 요청을 처리할 컨트롤러 객체가 있음!
                controllerResult = controller.handleRequest(request, response); // 일 해라.
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

            if (controllerResult instanceof MyPageInfo) { // forward 또는 redirect 둘 중 하나일 때 여기.(html 화면이 필요한 경우)
                MyPageInfo pageInfo = (MyPageInfo) controllerResult;
                if (pageInfo.isForward()) { // html 만들라고 jsp 한테 전달해서 화면 만들기
                    request.getRequestDispatcher("/WEB-INF/views" + pageInfo.getPath() + ".jsp").forward(request, response);
                } else { // jsp 에게 화면 만들라고 안 시키고 새로운 요청 유도하기. ~~~do 라고 리다이렉트 시키기.
                    response.sendRedirect(request.getContextPath() + pageInfo.getPath());
                }


            } else { // REST 요청에 대한 data 응답.(백앤드가 html 화면이 아니라 날 것의 data만 응답하는 경우가 여기.)
                /* Todo : 이부분에 대해서는 아직 안배움.. */
            }
        } catch (Exception e) {
            // throw new RuntimeException(e); // tomcat 의 에러페이지
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
