package com.giyeon.odhllm.domain.account.repository.Interface;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthTool {

    UserDetails findByNickName(String email);

}
