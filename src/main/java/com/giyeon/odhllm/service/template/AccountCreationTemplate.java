package com.giyeon.odhllm.service.template;

import com.giyeon.odhllm.service.DataEncoder;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.repository.Interface.Creation;
import org.springframework.stereotype.Component;

@Component
public class AccountCreationTemplate {
    public void createAccount(SignUpDto userInform, DataEncoder encoder, Creation creation) {
        User user = new User();
        user.fillInform(userInform);
        encoder.encodePwd(user);
        creation.create(user);
    }
}
