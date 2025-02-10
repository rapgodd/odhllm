package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import com.giyeon.odhllm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginDto, Model model){

        AuthTokenDto authTokenDto = loginService.authenticateUser(loginDto);
        model.addAttribute("accessToken", authTokenDto.getAccessToken());
        model.addAttribute("refreshToken", authTokenDto.getRefreshToken());

        return "home";

    }

    @GetMapping("/main")
    public String homepage(){
        return "home";
    }


}
