<%--
  Created by IntelliJ IDEA.
  User: ycjung
  Date: 2024-08-20(화)
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%-- 기존코드
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loginId = (String)session.getAttribute("loginId");
    if(loginId != null) {

%>
    <%=loginId%>님 로그인 중이시네요. 환영합니다.<br>
    <a href="<%=request.getContextPath()%>/member/logout.do">[로그아웃]</a>
<%
    } else {
%>
<a href="<%=request.getContextPath()%>/member/loginForm.do">[로그인 하러 가기]</a>
<a href="<%=request.getContextPath()%>/member/joinForm.do">[회원가입]</a>
<% } %>
<hr>
--%>
<%@ page import="com.auth0.jwt.JWT" %>
<%@ page import="com.auth0.jwt.algorithms.Algorithm" %>
<%@ page import="com.auth0.jwt.interfaces.DecodedJWT" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="com.grepp.boot.jwt.JwtTokenConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // JWT를 저장할 변수
    String accessToken = null;
    String refreshToken = null;
    String nickname = null;
    Long accessExp = null;
    Long refreshExp = null;

    // HttpServletRequest를 통해 쿠키에서 accessToken과 refreshToken 추출
    Cookie[] cookies = ((HttpServletRequest)request).getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("accessToken".equals(cookie.getName())) {
                accessToken = cookie.getValue();
            } else if ("refreshToken".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
            }
        }
    }

    try {
        if (accessToken != null) {
            // JWT를 디코딩하여 nickname과 accessExp 추출
            Algorithm algorithm = Algorithm.HMAC512(JwtTokenConstants.SECRET_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(accessToken);
            nickname = decodedJWT.getClaim("nickname").asString();
            accessExp = decodedJWT.getExpiresAt().getTime(); // Access 토큰 만료 시간을 밀리초로 얻음
        }

        if (refreshToken != null) {
            // JWT를 디코딩하여 refreshExp 추출
            Algorithm algorithm = Algorithm.HMAC512(JwtTokenConstants.SECRET_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(refreshToken);
            refreshExp = decodedJWT.getExpiresAt().getTime(); // Refresh 토큰 만료 시간을 밀리초로 얻음
        }
    } catch (Exception e) {
        e.printStackTrace(); // 예외 처리 로직
    }

    // 로그인 상태 확인
    if (nickname != null && accessExp != null && refreshExp != null) {
%>
<%=nickname%>님 로그인 중이시네요. 환영합니다.<br>
<a href="<%=request.getContextPath()%>/member/logout.do">[로그아웃]</a>
<p>엑세스 토큰 만료 시간: <span id="accessTokenExpiryTimer"></span>
    <button onclick="extendAccessToken()">30초 연장</button>
</p>
<p>리프레시 토큰 만료 시간: <span id="refreshTokenExpiryTimer"></span></p>
<script>
    // JavaScript로 토큰 만료 시간을 계산하여 타이머 표시
    var accessExpiryTime = <%=accessExp%>; // 서버에서 전달된 엑세스 토큰 만료 시간
    var refreshExpiryTime = <%=refreshExp%>; // 서버에서 전달된 리프레시 토큰 만료 시간

    function updateTimer(timerElementId, expiryTime) {
        var now = new Date().getTime();
        var timeRemaining = expiryTime - now;
        var element = document.getElementById(timerElementId);

        function calculateTime() {
            var seconds = Math.floor(timeRemaining / 1000);
            var minutes = Math.floor(seconds / 60);
            var hours = Math.floor(minutes / 60);
            seconds = seconds % 60;
            minutes = minutes % 60;

            element.innerText = hours + "h " + minutes + "m " + seconds + "s";

            if (timeRemaining > 0) {
                timeRemaining -= 1000; // 1초마다 감소
                setTimeout(calculateTime, 1000);
            } else {
                element.innerText = "Expired";
            }
        }

        calculateTime(); // 타이머 업데이트 시작
    }

    function extendAccessToken() {
        accessExpiryTime += 30000; // 30초 연장 (30,000 밀리초)
        updateTimer('accessTokenExpiryTimer', accessExpiryTime); // 타이머 업데이트
    }

    updateTimer('accessTokenExpiryTimer', accessExpiryTime); // 엑세스 토큰 타이머 업데이트
    updateTimer('refreshTokenExpiryTimer', refreshExpiryTime); // 리프레시 토큰 타이머 업데이트
</script>
<%
} else {
%>
<a href="<%=request.getContextPath()%>/member/loginForm.do">[로그인 하러 가기]</a>
<a href="<%=request.getContextPath()%>/member/joinForm.do">[회원가입]</a>
<% } %>
<hr>
