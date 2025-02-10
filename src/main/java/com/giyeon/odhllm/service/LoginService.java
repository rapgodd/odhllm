package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService{

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 30L;
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 30L;
    private static String JWT_SECRET_KEY;


    @Transactional
    public AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        byte[] bytes = JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        //엑세스 토큰 생성
        String accessToken = Jwts.builder()
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

        String refreshToken = Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                    .signWith(Keys.hmacShaKeyFor(bytes), SignatureAlgorithm.HS512)
                    .compact();

        AuthTokenDto authToken = AuthTokenDto.builder()
                                    .grantType("Bearer")
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .build();

        User user = (User) authenticate.getPrincipal();
        user.updateRefreshToken(authToken.getRefreshToken());
        System.out.println("엑세스토큰:"+authToken.getAccessToken());

        return authToken;
    }

    @Value("${jwt.secret}")
    public void setKey(String key){
        JWT_SECRET_KEY = key;
    }
}
