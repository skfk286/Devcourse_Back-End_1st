package com.grepp.boot.controller;


import com.grepp.boot.jwt.Jwt;
import com.grepp.boot.model.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tokens")
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    // 토큰 생성 API
    @PostMapping("/create")
    public ResponseEntity<Jwt> createTokens(@RequestBody Map<String, String> requestBody) {
        String loginId = requestBody.get("id");
        String nickname = requestBody.get("nickname");
        String socialAccessToken = requestBody.get("socialAccessToken");

        Jwt jwt = jwtTokenService.createTokens(loginId, nickname, socialAccessToken);
        return ResponseEntity.ok(jwt);
    }

    // 액세스 토큰 정보 확인 API
    @GetMapping("/info")
    public ResponseEntity<Jwt> getAccessTokenInfo(@RequestParam String accessToken) {
        Jwt jwt = jwtTokenService.getAccessTokenInfo(accessToken);
        return ResponseEntity.ok(jwt);
    }

    // 액세스 토큰 갱신 API
    @PostMapping("/refresh")
    public ResponseEntity<Jwt> refreshAccessToken(@RequestBody Map<String, String> requestBody) {
        String refreshToken = requestBody.get("refreshToken");

        Jwt newAccessToken = jwtTokenService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(newAccessToken);
    }
}