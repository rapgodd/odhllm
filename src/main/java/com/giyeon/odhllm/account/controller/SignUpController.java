package com.giyeon.odhllm.account.controller;

import com.giyeon.odhllm.account.domain.SignUpDto;
import com.giyeon.odhllm.account.service.SignUpService;
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
