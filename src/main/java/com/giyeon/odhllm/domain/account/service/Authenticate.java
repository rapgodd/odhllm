package com.giyeon.odhllm.domain.account.service;

import com.giyeon.odhllm.domain.account.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.account.dto.LoginRequestDto;

public interface Authenticate {

    AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto);

}
