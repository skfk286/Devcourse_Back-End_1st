<%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-20(화)
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loginId = (String)session.getAttribute("loginId");
    if(loginId != null) {

%>
    <%=loginId%>님 로그인 중이시네요. 환영합니다.
    <a href="<%=request.getContextPath()%>/logout.do">[로그아웃]</a>
<%
    } else {
%>
<a href="<%=request.getContextPath()%>/login.do">[로그인 하러 가기]</a>
<a href="#">[회원가입]</a>
<% } %>
<hr>
