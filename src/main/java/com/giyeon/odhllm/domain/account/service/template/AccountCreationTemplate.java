package com.giyeon.odhllm.domain.account.service.template;

import com.giyeon.odhllm.domain.DataEncoder;
import com.giyeon.odhllm.domain.account.domain.User;
import com.giyeon.odhllm.domain.account.dto.SignUpDto;
import com.giyeon.odhllm.domain.account.repository.Interface.AccountManaging;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationTemplate {
    public void createAccount(SignUpDto userInform, DataEncoder encoder, AccountManaging accountManaging) {
        User user = new User();
        user.fillInform(userInform);
        encoder.encodePwd(user);
        accountManaging.create(user);
    }
}
