package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final Login loginService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto form , HttpServletRequest request){

        AuthTokenDto authTokenDto = loginService.authenticateUser(form);

        if(authTokenDto.isExistedUser()){
            request.getSession().setAttribute("user", authTokenDto.getUserId());
        }

        return "index";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginRequestDto form){
        return "login";
    }

}
