package com.giyeon.odhllm.service.encoding;

import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.service.DataEncoder;
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
