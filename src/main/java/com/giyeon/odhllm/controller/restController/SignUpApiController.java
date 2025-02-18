package com.giyeon.odhllm.controller.restController;

import com.giyeon.odhllm.domain.dto.*;
import com.giyeon.odhllm.service.EmailSender;
import com.giyeon.odhllm.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpApiController {

    private final SignUpService signUpService;
    private final EmailSender emailSender;

    @PostMapping("/email")
    public ResponseEntity<ResponseDto<?>> isValidEmail(@RequestBody SignUpDto inform) {
        ValidationDto validationDto = signUpService.validateEmail(inform.getEmail());
        return wrapValidation(validationDto);
    }

    @PostMapping("/mail/code")
    public ResponseEntity<ResponseDto<?>> sendCode(@RequestBody MailVerificationDto mail){

        String notification = emailSender.send(mail);
        return ResponseDto.ok(notification);
    }


    @PostMapping("user/email/code")
    public ResponseEntity<ResponseDto<?>> isValidCode(@RequestBody CodeDto userCode) {
        ValidationDto validationDto = signUpService.validateCode(userCode);
        return wrapValidation(validationDto);
    }

    @PostMapping("/nickname")
    public ResponseEntity<ResponseDto<?>> isValidNickname(@RequestBody SignUpDto nickname){
        return signUpService.validateNickname(nickname.getNickname());
    }




    private static ResponseEntity<ResponseDto<?>> wrapValidation(ValidationDto validationDto) {
        if(validationDto.isValid()) {
            return ResponseEntity.ok()
                    .body(ResponseDto.<ValidationDto>builder().code(200).data(validationDto).build());
        }else{
            return ResponseEntity.badRequest().
                    body(ResponseDto.<ValidationDto>builder().code(404).data(validationDto).build());
        }
    }


}
