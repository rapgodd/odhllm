package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;

public interface Login {
    AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto);
}
