package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.repository.Interface.AccountManaging;
import com.giyeon.odhllm.service.template.AccountCreationTemplate;
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
