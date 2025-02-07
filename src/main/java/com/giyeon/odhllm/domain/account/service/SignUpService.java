package com.giyeon.odhllm.domain.account.service;

import com.giyeon.odhllm.domain.DataEncoder;
import com.giyeon.odhllm.domain.account.dto.SignUpDto;
import com.giyeon.odhllm.domain.account.repository.Interface.AccountManaging;
import com.giyeon.odhllm.domain.account.service.template.AccountCreationTemplate;
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
