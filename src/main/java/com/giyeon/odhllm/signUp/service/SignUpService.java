package com.giyeon.odhllm.signUp.service;

import com.giyeon.odhllm.signUp.DataHashEncoder;
import com.giyeon.odhllm.signUp.SignUpTemplate;
import com.giyeon.odhllm.signUp.domain.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final SignUpTemplate signUpTemplate;

    @Transactional
    public void save(SignUpDto userInform) {
        signUpTemplate.createAccount(userInform, new DataHashEncoder());
    }


}
