package com.giyeon.odhllm.exception;

import com.giyeon.odhllm.domain.dto.ResponseDto;
import com.giyeon.odhllm.exception.custom.EmptyUserInformException;
import com.giyeon.odhllm.exception.custom.WrongUserInformationException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyUserInformException.class)
    public String emptyUserInformException(EmptyUserInformException e, Model model){
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "responsiveIndex";
    }

    @ExceptionHandler(WrongUserInformationException.class)
    public String wrongUserInformException(WrongUserInformationException e, Model model){
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "responsiveLogin";
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ResponseDto<?>> wrongUserInformException(NestedRuntimeException e){
        return ResponseDto.failedApiCall(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "responsiveIndex";
    }

}
