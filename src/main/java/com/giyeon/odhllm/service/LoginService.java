package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import com.giyeon.odhllm.exception.custom.FilterException;
import com.giyeon.odhllm.exception.custom.WrongRefreshToken;
import com.giyeon.odhllm.repository.AccountRepository;
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
import java.util.List;
import java.util.Optional;

import static com.giyeon.odhllm.service.util.JwtUtil.*;

@Service
@RequiredArgsConstructor
public class LoginService{

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;

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
        AuthTokenDto authToken = createAuthDto(email, roles, bytes);

        User user = (User) authenticate.getPrincipal();
        user.updateRefreshToken(authToken.getRefreshToken());
        System.out.println("엑세스토큰:"+authToken.getAccessToken());

        return authToken;
    }

    @Value("${jwt.secret}")
    public void setKey(String key){
        JWT_SECRET_KEY = key;
    }

    @Transactional
    public AuthTokenDto reset(String refreshToken) {

        User user = accountRepository.areTokensEqual(refreshToken)
                .orElseThrow(()->new WrongRefreshToken("존재하지 않는 리프레시 토큰입니다."));

        AuthTokenDto authDto = createAuthDto(user.getEmail(),
                                    List.of(String.valueOf(user.getUserRole())),
                                    JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        user.updateRefreshToken(authDto.getRefreshToken());

        System.out.println(authDto);
        return authDto;

    }

    private AuthTokenDto createAuthDto(String email, List<String> roles, byte[] bytes) {
        return AuthTokenDto.builder()
                .grantType("Bearer")
                .accessToken(createAccessToken(email, roles, bytes,ACCESS_TOKEN_EXPIRATION_TIME))
                .refreshToken(createRefreshToken(email, bytes, REFRESH_TOKEN_EXPIRATION_TIME))
                .build();
    }

}
