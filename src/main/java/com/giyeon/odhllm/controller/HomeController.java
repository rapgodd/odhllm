package com.giyeon.odhllm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String homepage(){
        return "responsiveIndex";
    }

    @GetMapping("/")
    public String matchDefaultUrl(){
        return "responsiveIndex";
    }

}
