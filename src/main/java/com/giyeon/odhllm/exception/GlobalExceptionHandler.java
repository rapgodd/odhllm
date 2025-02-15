package com.giyeon.odhllm.exception;

import com.giyeon.odhllm.exception.custom.EmptyUserInformException;
import com.giyeon.odhllm.exception.custom.WrongUserInformationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyUserInformException.class)
    public String emptyUserInformException(EmptyUserInformException e, Model model){
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "home";
    }

    @ExceptionHandler(WrongUserInformationException.class)
    public String wrongUserInformException(WrongUserInformationException e, Model model){
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "login";
    }

}
