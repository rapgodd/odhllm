package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ResetController {

    private final LoginService loginService;

    @PostMapping("/refresh")
    public String refresh(@RequestBody AuthTokenDto refreshToken, Model model){

        AuthTokenDto authTokenDto = loginService.reset(refreshToken.getRefreshToken());
        model.addAttribute("accessToken", authTokenDto.getAccessToken());
        model.addAttribute("refreshToken", authTokenDto.getRefreshToken());
        System.out.println(model);

        return "home";

    }


}
