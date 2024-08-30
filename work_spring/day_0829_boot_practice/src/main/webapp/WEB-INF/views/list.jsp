<%@ page import="com.ycjung.shopping.model.dto.ShopProductDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-29(목)
  Time: 오후 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<table border="1">
    <tr>
        <th>상품번호</th>
        <th>상품이름</th>
        <th>설명</th>
        <th>가격</th>
        <th>수량</th>
        <th>등록날짜</th>
        <th>수정날짜</th>
    </tr>
    <%
    List<ShopProductDTO> products = (List<ShopProductDTO>) request.getAttribute("products");

    for (ShopProductDTO product : products) {
    %>
    <tr>
        <td><%=product.getProductId()%></td>
        <td><%=product.getProductName()%></td>
        <td><a href="<%=request.getContextPath()%>/product/read?id=<%=product.getProductId()%>"><%=product.getDescription()%></a></td>
        <td><%=product.getPrice()%></td>
        <td><%=product.getStockQuantity()%></td>
        <td><%=product.getCreatedAt()%></td>
        <td><%=product.getUpdatedAt()%></td>
    </tr>
    <%
    }
    %>
</table>
</body>
</html>
