package com.giyeon.odhllm.signUp.controller;

import com.giyeon.odhllm.signUp.domain.SignUpDto;
import com.giyeon.odhllm.signUp.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignUpDto signUpDto){
        signUpService.save(signUpDto);
        return "home";
    }

}
