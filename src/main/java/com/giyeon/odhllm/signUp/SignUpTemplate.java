package com.giyeon.odhllm.signUp;

import com.giyeon.odhllm.signUp.domain.SignUpDto;
import com.giyeon.odhllm.signUp.domain.User;
import org.springframework.stereotype.Component;

@Component
public class SignUpTemplate {
    public void createAccount(SignUpDto userInform, DataEncoder encoder) {
        User user = new User();
        user.fillInform(userInform);
        encoder.encodePwd(user);
    }

}
