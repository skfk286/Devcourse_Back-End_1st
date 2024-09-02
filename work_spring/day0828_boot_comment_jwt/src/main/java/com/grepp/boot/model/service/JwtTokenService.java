package com.grepp.boot.model.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.grepp.boot.jwt.InvalidJwtTokenException;
import com.grepp.boot.jwt.Jwt;
import com.grepp.boot.jwt.JwtTokenConstants;
import com.grepp.boot.jwt.JwtTokenVerifier;
import com.grepp.boot.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * JWT 토큰 라이브러리 : auth0 사용
 */
@Service
public class JwtTokenService {

    private int accessTokenExpMinutes = 30;
    private int refreshTokenExpMinutes = 120;

    public static final int SESSION_TIMEOUT_UNIT = 1000; // 1초 단위

    private JwtTokenVerifier jwtTokenVerifier;

    public Jwt createTokens(String loginId, String nickname, String socialAccessToken) {
        System.out.println("[createTokens param] : " + loginId + ", " + nickname + ", " + socialAccessToken);
        System.out.println("[createTokens] : " + DateUtils.getCurrentTimeFormatted()); // 현재 시간 출력
        String accessToken = createToken(loginId, nickname, socialAccessToken, accessTokenExpMinutes, "accessToken");
        String refreshToken = createToken(loginId, nickname, socialAccessToken, refreshTokenExpMinutes, "refreshToken");

        return Jwt.builder().
                accessToken(accessToken)
                .refreshToken(refreshToken)
                .loginId(loginId)
                .nickname(nickname)
                .accessTokenExp(new Date(System.currentTimeMillis() + SESSION_TIMEOUT_UNIT * accessTokenExpMinutes)) // 1초 단위
                .refreshTokenExp(new Date(System.currentTimeMillis() + SESSION_TIMEOUT_UNIT * refreshTokenExpMinutes)) // 1초 단위
                .build();
    }

    /**
     * 토큰 발급하기
     */
    public String createToken(String loginId, String nickname, String socialAccessToken, int expMinutes, String tokenType) {
        Date tokenExp;
        if(tokenType.equals("accessToken")) { // access token ext
            tokenExp = new Date(System.currentTimeMillis() + (SESSION_TIMEOUT_UNIT * expMinutes)); // 클라이언트 accessToken 만료시간
        }
        else { // refresh token ext
            tokenExp = new Date(System.currentTimeMillis() + (SESSION_TIMEOUT_UNIT * expMinutes)); // 클라이언트 refreshToken 만료시간
        }

        String createdToken = JWT.create()
                .withSubject(loginId)
                .withClaim("nickname", nickname)
                .withClaim("socialAccessToken", socialAccessToken)
                .withClaim("tokenType", tokenType)
                .withExpiresAt(tokenExp)
                .sign(Algorithm.HMAC512(JwtTokenConstants.SECRET_KEY));

        return createdToken;
    }

    /**
     * 토근 검증하기(정보 확인하기)
     */
    public Jwt getAccessTokenInfo(String receivedToken) {
        System.out.println("[getAccessTokenInfo] : " + DateUtils.getCurrentTimeFormatted()); // 현재 시간 출력
        String accessToken = null;
        Date accessTokenExp = null;
        String socialAccessToken = null;
        String loginId = null;
        String nickname = null;

        try {
            jwtTokenVerifier = new JwtTokenVerifier(JwtTokenConstants.SECRET_KEY);
            DecodedJWT decodedJWT = jwtTokenVerifier.verify(receivedToken);

            if (decodedJWT.getSubject() != null) {
                accessToken = receivedToken;
                accessTokenExp = decodedJWT.getExpiresAt();
                loginId = decodedJWT.getSubject();
                nickname = decodedJWT.getClaim("nickname").asString();
                socialAccessToken = decodedJWT.getClaim("socialAccessToken").asString();
            } else {
                throw new InvalidJwtTokenException(receivedToken);
            }
        }catch (JWTVerificationException ex) {
            throw new InvalidJwtTokenException(receivedToken);
        }

        return Jwt.builder()
                .accessToken(accessToken)
                .accessTokenExp(accessTokenExp)
                .loginId(loginId)
                .nickname(nickname)
                .socialAccessToken(socialAccessToken)
                .build();
    }

    /**
     * 토큰 갱신하기
     */
    public Jwt refreshAccessToken(String refreshToken) {
        System.out.println("[refreshAccessToken] : " + DateUtils.getCurrentTimeFormatted()); // 현재 시간 출력
        String accessToken = null;
        jwtTokenVerifier = new JwtTokenVerifier(JwtTokenConstants.SECRET_KEY);
        DecodedJWT decodedJWT = jwtTokenVerifier.verify(refreshToken);

        // 리프레쉬 토큰 검증 후 액세스 토큰 새로 발급
        if (decodedJWT.getClaim("nickname") != null && decodedJWT.getClaim("tokenType").asString().equals("refreshToken")) {
            accessToken = createToken(decodedJWT.getSubject(), decodedJWT.getClaim("nickname").asString()
                    , decodedJWT.getClaim("socialAccessToken").asString(), accessTokenExpMinutes, "accessToken");
        } else {
            throw new InvalidJwtTokenException(refreshToken);
        }

        return Jwt.builder()
                .accessToken(accessToken)
                .accessTokenExp(new Date(System.currentTimeMillis() + SESSION_TIMEOUT_UNIT * accessTokenExpMinutes))
                .build();
    }
}
