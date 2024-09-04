package com.ycjung.day0904.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Filter 는 Dispatcher Servlet 보다 먼저 실행 된다.
/*
    더 해볼만한 것(추가 과제!)
    1. 사용자, 관리자 권한 설정
    2. auth2 소셜 로그인 설정
*/
@Component
public class YcjungFilter extends OncePerRequestFilter { // 리퀘스트 요청 한 번당 처리 필터 상속

    @Autowired
    private MyJwtTokenProvider myJwtTokenProvider;

    // TODO : 유요한 토큰을 클라이언트에서 들고 오는지 검사하고 싶다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            System.out.println("start ycjung filter : " + token);
            if (token != null) {
                String username = myJwtTokenProvider.validateUserToken(token); // 유효하지 않은 토큰인 경우 throw Exception 발생된다.
                // 토큰이 유효하다면 인증이 완료된거다.

                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username, // 지금처럼 문자열 말고 다른 것도 들어갈 수 있는데, 예제에서는 UserDetails 라는 오브젝트를 넣는 경우가 많다. 지금은 안함.
                        null, // JWT 인증일 했기 때문에 넣지 않음.
                        AuthorityUtils.NO_AUTHORITIES // 로그인이 된 상태에서도 관리자, 일반 사용자 등의 권한이 달라질 수 있다. 여기서 설정
                );

                // 토큰에다가 기본적인 인증정보 외에 더 부가적으로 담아놓고 싶은 내용이 있으면 아래 Detail 에서 더 담는 작업 수행 가능
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // 비어있는 컨텍스트 생성
                securityContext.setAuthentication(authenticationToken); // 컨텍스트에 토큰 담기
                SecurityContextHolder.setContext(securityContext); // 홀더에 컨텍스트 고정
            }
        } catch (Exception ex) {
            // 토큰이 유효하지 않아서 인증 불가 오류가 발생
            // DispatcherServlet 으로 안가야 되고, ResponseEntity 를 리턴하는 작업을 부탁할 수 없다. 그러므로 아래와 같은 작업으로..
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); // response 는 기본 설정이 html 응답으로 되어 있으므로..

            Map<String, Object> body = new HashMap<>(); // front 한테 인증 불가에 대한 에러 메세지를 응답하고 싶어서..
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized 인증 불가");
            body.put("message", ex.getMessage());
            body.put("path", request.getServletPath());

            ObjectMapper mapper = new ObjectMapper(); // 평소에 @RequestBody, @ResponseBody 처리하면서 자바 <-> json 작업할 때 쓰이던 라이브러리
            mapper.writeValue(response.getOutputStream(), body);

        }
        filterChain.doFilter(request, response); // 때에 따라서는 아래 예외 발생시에도 나머지 필터를 더 진행해야될 수도 있음.
    }

    // JWT 토큰을 활용하는 과정에서 토큰 형식이 아래처럼 진행되는 경우가 많음
    // Authorization : Bearer(접두어를 붙이는게 관행?)
    // Bearer 글자가 기능적인 역할은 없는데 토큰 개발하는 프론트 쪽에서 관습적으로 항상 붙이는 것 같다.
    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
