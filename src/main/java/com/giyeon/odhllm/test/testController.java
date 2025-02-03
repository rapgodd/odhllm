package com.giyeon.odhllm.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("/main")
    public String homepage(){
        return "home";
    }

}
