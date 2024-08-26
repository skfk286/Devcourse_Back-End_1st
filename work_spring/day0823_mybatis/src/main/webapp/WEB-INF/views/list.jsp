<%@ page import="com.grepp.model.dto.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-20(화)
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판목록</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<table border="1">
    <tr>
        <th>순번</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록 날짜</th>
        <th>조회수</th>
        <th>내용</th>
    </tr>
    <%
        // List<BoardDTO> bList = (List<BoardDTO>) request.getAttribute("bList");
        Map<String, Object> pageData = (Map<String, Object>) request.getAttribute("pageData");
        List<BoardDTO> bList = (List<BoardDTO>) pageData.get("bList");

        for(BoardDTO b: bList){
    %>
    <tr>
        <td><%=b.getNo()%></td>
        <td><a href="<%=request.getContextPath()%>/board/read.do?no=<%=b.getNo()%>"><%=b.getTitle()%></a></td>
        <td><%=b.getWriter()%></td>
        <td><%=b.getRegDate()%></td>
        <td><%=b.getReadCount()%></td>
        <td><%=b.getContent()%></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="5">페이지가 나타날 영역</td>
    </tr>
</table>
<a href="<%=request.getContextPath()%>/main.do">[메인화면으로]</a>
</body>
</html>
