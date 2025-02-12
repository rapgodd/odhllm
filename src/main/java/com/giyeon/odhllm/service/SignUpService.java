package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.repository.Interface.Creation;
import com.giyeon.odhllm.repository.UserRepository;
import com.giyeon.odhllm.service.template.AccountCreationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final DataEncoder dataEncoder;
    private final AccountCreationTemplate accountCreationTemplate;
    private final Creation creation;
    private final UserRepository userRepository;

    public void save(SignUpDto userInform) {
        accountCreationTemplate.createAccount(userInform, dataEncoder, creation);
    }

    public String validateEmail(String email) {
        if(email.contains("@") && email.contains(".")) {
            if(userRepository.findByEmail(email).isEmpty()){
                return "사용 가능한 이메일입니다.";
            }else{
                return "이미 사용중인 이메일입니다.";
            }
        }else{
            return "이메일 형식이 올바르지 않습니다.";
        }
    }
}
