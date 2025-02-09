package com.giyeon.odhllm.domain.jwt;

import com.giyeon.odhllm.domain.SecurityConfig;
import com.giyeon.odhllm.domain.account.exception.custom.FilterException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {
    private final String JWT_SECRET_KEY;

    public JwtFilter(String jwtSecretKey) {
        this.JWT_SECRET_KEY = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header  = request.getHeader("Authorization");
        
        
        extractToken(header).ifPresentOrElse(token -> {
            //헤더에 토큰 존재 확인
            byte[] secretBytes = JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);

            //토큰에서 유저 정보 추출 -> 비교(hidden) -> if 인증 성공 -> 클레임 획득
            Claims claims = getHandledClaim(token, secretBytes);

            // 클레임에서 정보 추출 -> 인증객체 생성
            createSession(claims);

            // 다음 필터로 넘어가기
            System.out.println("JWT 인증 통과");
            handOverToNext(request, response, filterChain);
        }, () -> {
            throw new FilterException("JWT 토큰이 존재하지 않습니다.");
        });


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){

        String path = request.getServletPath();
        AntPathMatcher pathMatcher = new AntPathMatcher();

        for (String s : SecurityConfig.PERMIT_URLS) {
            if (pathMatcher.match(s, path)) {
                return true;
            }
        }
        return false;

    }


    private void handOverToNext(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        }catch (Exception e){
            throw new FilterException("JWT필터 다음 필터 체인 시도가 실패했습니다.");
        }
    }

    private void createSession(Claims claims) {
        String email = claims.getSubject();
        String role = String.valueOf(claims.get("role"));

        // 사용자 정보를 기반으로 Authentication 객체 생성
        Authentication auth = new UsernamePasswordAuthenticationToken(email, "", AuthorityUtils.commaSeparatedStringToAuthorityList(role));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Claims getHandledClaim(String token, byte[] secretBytes) {
        Claims claims;
        try {
            claims = extract(token, secretBytes);
        }catch (Exception e){
            throw new FilterException("JWT 토큰 검증과정에서 오류가 발생했습니다.");
        }
        return claims;
    }

    private Claims extract(String token, byte[] secretBytes) {
        Claims claims;
        claims = Jwts.parserBuilder()
               .setSigningKey(Keys.hmacShaKeyFor(secretBytes))
               .build()
               .parseClaimsJws(token)
               .getBody();
        return claims;
    }

    private Optional<String> extractToken(String token) {
        if(StringUtils.hasText(token)&& token.startsWith("Bearer")){
            return Optional.ofNullable(token.substring(7));
        }else{
            return Optional.ofNullable(null);
        }
    }


}
