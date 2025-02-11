package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.AuthTokenDto;
import com.giyeon.odhllm.domain.dto.LoginRequestDto;
import com.giyeon.odhllm.domain.dto.MailVerificationDto;
import com.giyeon.odhllm.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;

    @PostMapping("/mail/code")
    public String sendCode(@RequestBody MailVerificationDto mail, Model model){

        String notification = emailSender.send(mail);
        model.addAttribute("notification", notification);

        return "home";
    }


}
