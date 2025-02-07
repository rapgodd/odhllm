package com.giyeon.odhllm.domain;

import com.giyeon.odhllm.domain.account.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class DataHashEncoder implements DataEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void encodePwd(User user) {
        user.hashPassword(bCryptPasswordEncoder);
    }
}
