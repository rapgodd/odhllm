package com.giyeon.odhllm.service;

import com.giyeon.odhllm.controller.Login;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import com.giyeon.odhllm.exception.custom.WrongUserInformationException;
import com.giyeon.odhllm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements Login {

    private final UserRepository userRepository;

    @Override
    public AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto) {

        userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new WrongUserInformationException("잘못된 이메일 또는 비밀번호입니다."));

        AuthTokenDto response = new AuthTokenDto();
        response.setExistedUser(true);
        return response;

    }



}
