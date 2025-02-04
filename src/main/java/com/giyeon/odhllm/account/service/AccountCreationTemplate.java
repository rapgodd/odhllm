package com.giyeon.odhllm.account.service;

import com.giyeon.odhllm.account.domain.SignUpDto;
import com.giyeon.odhllm.account.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationTemplate {
    public void createAccount(SignUpDto userInform, DataEncoder encoder, AccountManaging accountManaging) {
        User user = new User();
        user.fillInform(userInform);
        encoder.encodePwd(user);
        accountManaging.save(user);
    }
}
