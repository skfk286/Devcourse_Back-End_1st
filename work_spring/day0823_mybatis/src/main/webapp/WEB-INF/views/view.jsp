<%@ page import="com.grepp.model.dto.BoardDTO" %>
<%@ page import="com.grepp.model.dto.FileDTO" %>
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
    <title>글 상세화면</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<%
    BoardDTO bbb = (BoardDTO) request.getAttribute("bbb");
%>
<table border="1">
    <tr>
        <td>글번호 :</td>
        <td><%=bbb.getNo()%>
        </td>
    </tr>
    <tr>
        <td>제목 :</td>
        <td><%=bbb.getTitle()%>
        </td>
    </tr>
    <tr>
        <td>작성자 :</td>
        <td><%=bbb.getWriter()%>
        </td>
    </tr>
    <tr>
        <td>작성일시 :</td>
        <td><%=bbb.getRegDate()%>
        </td>
    </tr>
    <tr>
        <td>조회수 :</td>
        <td><%=bbb.getReadCount()%>
        </td>
    </tr>
    <tr>
        <td>내용 :</td>
        <td><%=bbb.getContent()%>
        </td>
    </tr>
    <%
        if (bbb.getFileDTOList() != null && !bbb.getFileDTOList().isEmpty()) {
    %>
    <tr>
        <td colspan="2">
            <%
                for (FileDTO f : bbb.getFileDTOList()) {
            %>
            <a href="<%=request.getContextPath()%>/board/download.do?fno=<%=f.getFno()%>">첨부파일 : <%=f.getOriginalName()%></a><br>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>

<a href="#">[수정하기]</a>
<a href="<%=request.getContextPath()%>/board/list.do">[게시판 목록으로]</a>
<div id="divComment">
    <table border="1" id="tableComment">
        <tr><td>작성된 댓글이 없습니다.</td></tr>
    </table>
    작성자 : <input type="text" id="uuu"/><br>
    댓글 :   <input type="text" id="ttt"/><br>
    <button id="btnSend">댓글등록</button>
    <%--<button id="btnReceive">댓글 얻어오기</button>--%>
</div>
<script>
    async function getComments() {
        // let url = '<%=request.getContextPath()%>/comment/list.do?bno=<%=bbb.getNo()%>'
        // let url = '<%=request.getContextPath()%>/comment/list.do/<%=bbb.getNo()%>' // @PathVariable 방식.
        let url = 'http://localhost:8888/comment/list.do/<%=bbb.getNo()%>' // CORS

        let resp = await fetch(url);
        let result = await resp.json(); // response 헤더에 Content-type 이 application/json 으로 되어 있었을 떄 json 함수로 파싱해서 받을 수 있다.
        console.log('resp', resp);
        console.log('result', result);
        let table = document.getElementById("tableComment");
        if(result.length > 0)
            table.innerHTML = '';

        for(let i=0; i<result.length; i++) {
            let tr = document.createElement("tr");
            let tdWriter = document.createElement("td");
            let tdContent = document.createElement("td");
            tdWriter.appendChild(document.createTextNode(result[i].cwriter));
            tdContent.appendChild(document.createTextNode(result[i].ccontent));
            tr.appendChild(tdWriter);
            tr.appendChild(tdContent);
            table.appendChild(tr);
        }
    }
    getComments();
    // 댓글 가져오기 버튼 실행.
    <%--document.getElementById('btnReceive').onclick = async function () {--%>
    <%--    // let url = '<%=request.getContextPath()%>/comment/list.do?bno=<%=bbb.getNo()%>'--%>
    <%--    let url = '<%=request.getContextPath()%>/comment/list.do/<%=bbb.getNo()%>' // @PathVariable 방식.--%>

    <%--    let resp = await fetch(url);--%>
    <%--    let result = await resp.json(); // response 헤더에 Content-type 이 application/json 으로 되어 있었을 떄 json 함수로 파싱해서 받을 수 있다.--%>
    <%--    console.log('resp', resp);--%>
    <%--    console.log('result', result);--%>
    <%--    let table = document.getElementById("tableComment");--%>
    <%--    table.innerHTML = '';--%>
    <%--    for(let i=0; i<result.length; i++) {--%>
    <%--        let tr = document.createElement("tr");--%>
    <%--        let tdWriter = document.createElement("td");--%>
    <%--        let tdComment = document.createComment("td");--%>
    <%--        tdWriter.appendChild(document.createTextNode(result[i].cwriter));--%>
    <%--        tdComment.appendChild(document.createTextNode(result[i].ccomment));--%>
    <%--        tr.appendChild(tdWriter);--%>
    <%--        tr.appendChild(tdComment);--%>
    <%--        table.appendChild(tr);--%>
    <%--    }--%>
    <%--}--%>

    // 댓글 등록 버튼 실행.
    document.getElementById('btnSend').onclick = async function () { // 비동기 함수
        let uuu = document.getElementById('uuu').value;
        let ttt = document.getElementById('ttt').value;

        // alert("작성자: " + uuu + ", 내용: " + ttt + " 라는 내용을 백엔드 서버에 전송해주세요.")
        let url = '<%=request.getContextPath()%>/comment/write.do?cwriter=' + uuu + '&ccontent=' + ttt + '&bno=' + <%=bbb.getNo()%>;

        // fetch 를 실행하면 백엔드에게 데이터를 보내고 response 를 아직 안받았어도 아래 문장으로 내려가준다.
        let resp = await fetch(url);
        // 응답 response 객체에서 유용한 데이터를 뽑아내는 과정도 시간이 걸릴 수 있으니 기다렸다가 아래 문장을 실행한다.
        let respData = await resp.text();

        // alert('백엔드 응답! : ' + respData)

        getComments();
    }
</script>
</body>
</html>
