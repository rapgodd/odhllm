package com.giyeon.odhllm.service.util;

import com.giyeon.odhllm.exception.custom.FilterException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.List;

public final class JwtUtil {

    private JwtUtil() {

    }

    public static String createAccessToken(String email, List<String> roles, byte[] bytes, Long ACCESS_TOKEN_EXPIRATION_TIME) {
        return Jwts.builder()
                // subject = 토큰 제목으로 주로 식별자를 사용하기 때문에 email로 지정
                .setSubject(email)
                // clain = 내용으로 권한을 삽입했습니다.
                .claim("role", roles)
                // 발급 시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 만료 시간
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(bytes), SignatureAlgorithm.HS512)
                .compact();
    }

    public static String createRefreshToken(String email, byte[] bytes, Long REFRESH_TOKEN_EXPIRATION_TIME) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(bytes), SignatureAlgorithm.HS512)
                .compact();
    }

    public static Claims getHandledClaim(String token, byte[] secretBytes) {
        Claims claims;
        try {
            claims = extract(token, secretBytes);
        }catch (Exception e){
            throw new FilterException("JWT 토큰 검증과정에서 오류가 발생했습니다.");
        }
        return claims;
    }

    public static Claims extract(String token, byte[] secretBytes) {
        Claims claims;
        claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }





}
