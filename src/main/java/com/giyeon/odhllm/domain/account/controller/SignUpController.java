package com.giyeon.odhllm.domain.account.controller;

import com.giyeon.odhllm.domain.account.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.account.dto.LoginRequestDto;
import com.giyeon.odhllm.domain.account.dto.SignUpDto;
import com.giyeon.odhllm.domain.account.service.Authenticate;
import com.giyeon.odhllm.domain.account.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignUpDto signUpDto, Model model){
        signUpService.save(signUpDto);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "home";
    }


}
