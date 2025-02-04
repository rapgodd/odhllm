package com.giyeon.odhllm.account;

import com.giyeon.odhllm.account.domain.User;
import com.giyeon.odhllm.account.service.DataEncoder;
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
