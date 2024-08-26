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
    <title>글 작성화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<br>
<!-- 원래는 enctype="application/x-www-form-urlencoded" 가 default-->
<!-- enctype="multipart/form-data" : 다양한 형태로 데이터를 전달하겠다는 의미 -->
<form action="<%=request.getContextPath()%>/board/write.do" method="post" enctype="multipart/form-data">
    제목 : <input type="text" name="title"><br>
    내용 : <textarea name="content"></textarea><br>
    <input type="file" name="uploadFile"/><br>
    <input type="file" name="uploadFile"/><br>
    <input type="submit" value="작성완료">
</form>
</body>
</html>
