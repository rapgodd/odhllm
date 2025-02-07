package com.giyeon.odhllm.domain.account.controller;

import com.giyeon.odhllm.domain.account.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.account.dto.LoginRequestDto;
import com.giyeon.odhllm.domain.account.service.Authenticate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final Authenticate Authenticate;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginDto, Model model){

        AuthTokenDto authTokenDto = Authenticate.authenticateUser(loginDto);
        model.addAttribute("accessToken", authTokenDto.getAccessToken());
        model.addAttribute("refreshToken", authTokenDto.getRefreshToken());

        return "home";

    }

    @GetMapping("/main")
    public String homepage(){
        return "home";
    }


}
