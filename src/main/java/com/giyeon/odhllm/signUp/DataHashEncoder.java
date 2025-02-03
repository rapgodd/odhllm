package com.giyeon.odhllm.signUp;

import com.giyeon.odhllm.signUp.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataHashEncoder implements DataEncoder{
    @Override
    public void encodePwd(User user) {
        user.hashPassword(user, new BCryptPasswordEncoder());
    }
}
