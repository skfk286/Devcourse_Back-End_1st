<%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-21(수)
  Time: 오후 3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
저는 자바이지만 html 만들기 장인입니다.<br>
먼저 작업한 자바 데이터를 받아서 html 사이에 넣겠습니다.<br>
필요한 경우 조건문이나 반복문도 활용 가능하죠!<br>
다만 그렇게 되면 html 태그가 섞여 있어서 제법 지저분해집니다...<br>
<%=request.getAttribute("msg")%> <br>
현재 시간 : <%=request.getAttribute("now")%>
</body>
</html>
