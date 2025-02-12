package com.giyeon.odhllm.controller.restController;

import com.giyeon.odhllm.domain.dto.CodeDto;
import com.giyeon.odhllm.domain.dto.ResponseDto;
import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.domain.dto.ValidationDto;
import com.giyeon.odhllm.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpApiController {

    private final SignUpService signUpService;

    @PostMapping("/email")
    public ResponseEntity<ResponseDto<?>> isValidEmail(@RequestBody SignUpDto inform) {
        ValidationDto validationDto = signUpService.validateEmail(inform.getEmail());
        return wrapValidation(validationDto);
    }

    @PostMapping("user/email/code")
    public ResponseEntity<ResponseDto<?>> isValidCode(@RequestBody CodeDto userCode) {
        ValidationDto validationDto = signUpService.validateCode(userCode);
        return wrapValidation(validationDto);
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
