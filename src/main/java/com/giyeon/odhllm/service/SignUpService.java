package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.CodeDto;
import com.giyeon.odhllm.domain.dto.ResponseDto;
import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.domain.dto.ValidationDto;
import com.giyeon.odhllm.repository.Interface.Creation;
import com.giyeon.odhllm.repository.UserRepository;
import com.giyeon.odhllm.service.template.AccountCreationTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final DataEncoder dataEncoder;
    private final AccountCreationTemplate accountCreationTemplate;
    private final Creation creation;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(SignUpDto userInform) {
        accountCreationTemplate.createAccount(userInform, dataEncoder, creation);
    }

    public ValidationDto validateEmail(String givenEmail) {
        if(checkEmailFormat(givenEmail)) {
            return isUnique(givenEmail);
        }
        return ValidationDto.builder().valid(false).responseData("유효하지 않은 형식입니다.").build();
    }

    private ValidationDto isUnique(String givenEmail) {
        if(userRepository.findByEmail(givenEmail).isEmpty()){
            return ValidationDto.builder().valid(true).responseData("유효한 이메일입니다").build();
        }else{
            return ValidationDto.builder().valid(false).responseData("이미 존재하는 이메일입니다.").build();
        }
    }

    private boolean checkEmailFormat(String givenEmail) {
        return givenEmail.contains("@") && givenEmail.contains(".");
    }

    public ValidationDto validateCode(CodeDto typedCode) {

        String answerCode = (String)redisTemplate.opsForValue().get(typedCode.getEmail());

        if(Optional.ofNullable(answerCode).isEmpty()){
            return ValidationDto.builder().valid(false).responseData("인증 실패").build();
        }else{
            if(answerCode.equals(typedCode.getCode())) {
                redisTemplate.delete(typedCode.getEmail());
                return ValidationDto.builder().valid(true).responseData("인증 완료").build();
            }else{
                return ValidationDto.builder().valid(false).responseData("인증 실패").build();
            }
        }
    }

    public ResponseEntity<ResponseDto<?>> validateNickname(String nickname) {

         if(userRepository.findByNickname(nickname).isPresent()){
             return ResponseDto.duplicatedInput("이미 존재하는 닉네임입니다");
         }else{
             return ResponseDto.ok("사용 가능한 닉네임입니다.");
         }

    }
}
