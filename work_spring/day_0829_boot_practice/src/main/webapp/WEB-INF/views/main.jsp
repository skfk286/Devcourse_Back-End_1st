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
    <title>메인화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<h2>백앤드 데브코스 쇼핑몰 실습 화면</h2>
<a href="<%=request.getContextPath()%>/product/list">[상품 목록보기]</a>
<a href="<%=request.getContextPath()%>/product/addition">[상품 추가]</a>

</body>
</html>
