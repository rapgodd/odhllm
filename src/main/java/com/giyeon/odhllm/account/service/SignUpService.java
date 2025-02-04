package com.giyeon.odhllm.account.service;

import com.giyeon.odhllm.account.domain.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final DataEncoder dataEncoder;
    private final AccountCreationTemplate accountCreationTemplate;
    private final AccountManaging accountManaging;

    public void save(SignUpDto userInform) {
        accountCreationTemplate.createAccount(userInform, dataEncoder, accountManaging);
    }

}
