package com.giyeon.odhllm.controller.restController;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpApiController {

    private final SignUpService signUpService;

    @PostMapping("/email")
    public String validateEmail(@RequestBody SignUpDto inform, Model model) {
        String result = signUpService.validateEmail(inform.getEmail());
        System.out.println("\n"+result+"\n");
        return result;
    }


}
