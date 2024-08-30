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

    <button id="btnAddFile">파일추가</button>
    <div id="divFiles"></div>
<%--    <input type="file" name="uploadFile"/><br>--%>
<%--    <input type="file" name="uploadFile"/><br>--%>
    <input type="submit" value="작성완료">
</form>

<script> /* 원하는 형태로 첨부파일을 추가한다.(가변적인 상황) */
    document.getElementById('btnAddFile').onclick = function () {
        let div = document.createElement('div');
        let input = document.createElement('input'); // <input type="file" name="uploadFile"/>
        input.setAttribute('type', 'file');
        input.setAttribute('name', 'uploadFile');

        // 삭제 버튼 생성
        let btnDelete = document.createElement('button');
        btnDelete.setAttribute('type', 'button');
        btnDelete.setAttribute('value', '삭제');
        // btnDelete.textContent = '삭제'; // 버튼에 표시될 텍스트
        btnDelete.appendChild(document.createTextNode('삭제'));
        btnDelete.onclick = function () {
            // div.remove(); // 파일 선택 버튼과 삭제 버튼을 포함한 div 요소 삭제 (이렇게 해도 동작됨)
            this.parentElement.remove(); // this 는 삭제버튼. 부모는 div 태그 찾아서 삭제하는 형태
        };

        div.appendChild(input); // <div> <input type="file" name="uploadFile"/> </div> 형태로 만들어짐.
        div.appendChild(btnDelete);
        document.getElementById('divFiles').appendChild(div);

        return false; // form 이 갑자기 파일추가 버튼 눌렀다고 submit 하는 상황을 막아준다.
    }
</script>

</body>
</html>
