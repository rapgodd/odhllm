package com.giyeon.odhllm.account.exception;

import com.giyeon.odhllm.account.exception.custom.EmptyUserInformException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(EmptyUserInformException.class)
    public String emptyUserInformException(EmptyUserInformException e, Model model){
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "home";
    }

}
