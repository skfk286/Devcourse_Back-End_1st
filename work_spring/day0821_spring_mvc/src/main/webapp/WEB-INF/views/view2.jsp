<%@ page import="org.springframework.web.servlet.tags.Param" %>
<%@ page import="com.grepp.model.ParamDTO" %><%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-21(수)
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int gugudan = (Integer) request.getAttribute("gugudan");
    for(int i=1;i<10;i++) {
%>
        <h5><%=gugudan%> x <%=i %> = <%=gugudan * i%></h5>
<%
    }
%>
당신이 /forward/str 한테 보낸 파라미터는 dto에 담겨 나한테까지 전달되었습니다.<br>
<%
    ParamDTO paramDTO = (ParamDTO) request.getAttribute("param");
%>
param1 = <%=paramDTO.getParam1()%> <br>
param2 : <%=paramDTO.getParam2()%> <br>
</body>
</html>
