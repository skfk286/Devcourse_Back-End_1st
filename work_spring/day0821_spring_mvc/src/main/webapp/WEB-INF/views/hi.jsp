<%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-21(수)
  Time: 오전 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hi 가 만든 html 화면을 보게 됩니다!<br>
<form action="<%=request.getContextPath()%>/bye" method="get">
    param1 : <input type="text" name="param1"><br>
    param2 : <input type="text" name="param2"><br>
    <input type="submit" value="전송">
</form>
</body>
</html>
