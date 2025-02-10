package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    private final SignUpService signUpService;

    @PostMapping("/")
    public String signUp(@RequestBody SignUpDto signUpDto, Model model){
        signUpService.save(signUpDto);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "home";
    }


}
