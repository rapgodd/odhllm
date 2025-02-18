package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute SignUpDto signUpDto){
        signUpService.save(signUpDto);
        return "redirect:/login";
    }

    @GetMapping("/signUp")
    public String goSignUpPage(@ModelAttribute("signup") SignUpDto signUpDto){

        return "responsiveSignup";
    }

}
